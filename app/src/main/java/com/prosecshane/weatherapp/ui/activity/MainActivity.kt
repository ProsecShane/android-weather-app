package com.prosecshane.weatherapp.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import com.prosecshane.weatherapp.compose.elements.InAppNotification
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme
import com.prosecshane.weatherapp.data.cityNamesRussian
import com.prosecshane.weatherapp.data.datasource.hardcoded.initialValues
import com.prosecshane.weatherapp.data.model.Entry
import com.prosecshane.weatherapp.data.sharedprefs.SpApi
import com.prosecshane.weatherapp.data.sharedprefs.SpConstants.CELSIUS
import com.prosecshane.weatherapp.data.sharedprefs.SpConstants.CITY
import com.prosecshane.weatherapp.data.sharedprefs.SpConstants.REFRESH
import com.prosecshane.weatherapp.data.sharedprefs.SpConstants.THEME
import com.prosecshane.weatherapp.ui.fragment.location.LocationDialog
import com.prosecshane.weatherapp.ui.fragment.main.MainFragment
import com.prosecshane.weatherapp.ui.fragment.settings.SettingsDialog
import com.prosecshane.weatherapp.ui.stateholders.WeatherAppViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val spApi = SpApi(this)
        val viewModel = WeatherAppViewModel(
            initChosenCity = spApi.get(CITY, 0),
            rememberCityAndTime = { city, time ->
                spApi.set(CITY, city)
                spApi.set(REFRESH, time)
            },
            getCity = { spApi.get(CITY, 0) },
            getTheme = { spApi.get(THEME, 0) },
            getCelsius = { spApi.get(CELSIUS, 0) },
            getRefresh = { spApi.get(REFRESH, 0L) },
            onSuccessfulUpdateEntriesCallback = {},
            onUnsuccessfulUpdateEntriesCallback = {},
        )

        setContent {
            val showLocationDialog: MutableState<Boolean> =
                remember { mutableStateOf(false) }
            val showSettingsDialog: MutableState<Boolean> =
                remember { mutableStateOf(false) }
            val showNoInternetNotification: MutableState<Boolean> =
                remember { mutableStateOf(false) }

            viewModel.onSuccessfulUpdateEntriesCallback = {
                showNoInternetNotification.value = false
            }
            viewModel.onUnsuccessfulUpdateEntriesCallback = {
                showNoInternetNotification.value = true
            }

            val chosenCity = viewModel.city.collectAsState()
            val themeChoice = viewModel.theme.collectAsState()
            val celsius = viewModel.celsius.collectAsState()
            val refreshTime = viewModel.refresh.collectAsState()

            // TODO: get from Local or Network Data Source
            val weatherData: State<List<Entry>> = viewModel.entries.collectAsState(initialValues)

            WeatherAppTheme(
                when (themeChoice.value) {
                    1 -> false
                    2 -> true
                    else -> isSystemInDarkTheme()
                }
            ) {
                MainFragment(
                    weatherData.value,
                    chosenCity.value,
                    celsius.value,
                    refreshTime.value,
                    { showLocationDialog.value = true },
                    { showSettingsDialog.value = true },
                    { lifecycleScope.launch(Dispatchers.IO) {
                        viewModel.updateEntries(
                            chosenCity = chosenCity.value,
                            callback = {
                                viewModel.updateCity()
                                viewModel.updateRefresh()
                            },
                        )
                    } },
                )

                if (showLocationDialog.value) {
                    LocationDialog(
                        cityNames = cityNamesRussian,
                        chosenCity = chosenCity.value,
                        onCityChosen = {
                            lifecycleScope.launch(Dispatchers.IO) {
                                viewModel.updateEntries(
                                    it,
                                    callback = {
                                        viewModel.updateCity()
                                        viewModel.updateRefresh()
                                    },
                                )
                            }
                        },
                        onDismissRequest = {
                            showLocationDialog.value = false
                        }
                    )
                }

                if (showSettingsDialog.value) {
                    SettingsDialog(
                        themeChoice = themeChoice,
                        celsius = celsius,
                        onThemeClickCallback = { x ->
                            lifecycleScope.launch(Dispatchers.IO) {
                                spApi.set(THEME, x)
                                viewModel.updateTheme()
                            }
                        },
                        onCelsiusClickCallback = { x ->
                            lifecycleScope.launch(Dispatchers.IO) {
                                spApi.set(CELSIUS, x)
                                viewModel.updateCelsius()
                            }
                        },
                        onDismissRequest = { showSettingsDialog.value = false },
                    )
                }

                if (showNoInternetNotification.value) {
                    InAppNotification(
                        text = "Нет подключения к Интернету!",
                        onDismissCallback = {
                            showNoInternetNotification.value = false
                        },
                    )
                }
            }
        }
    }
}
