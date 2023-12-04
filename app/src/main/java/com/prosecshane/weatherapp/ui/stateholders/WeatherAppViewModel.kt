package com.prosecshane.weatherapp.ui.stateholders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prosecshane.weatherapp.data.datasource.hardcoded.initialValues
import com.prosecshane.weatherapp.data.datasource.local.room.WeatherAppDatabase
import com.prosecshane.weatherapp.data.model.Entry
import com.prosecshane.weatherapp.data.repository.WeatherAppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Main app state holder, view model
class WeatherAppViewModel(
    private val initChosenCity: Int,
    rememberCityAndTime: (Int, Long) -> Unit,
    getCity: () -> Int,
    getTheme: () -> Int,
    getCelsius: () -> Int,
    getRefresh: () -> Long,
    private var onSuccessfulUpdateEntriesCallback: () -> Unit,
    private var onUnsuccessfulUpdateEntriesCallback: () -> Unit,
    databaseMaker: () -> WeatherAppDatabase,
) : ViewModel() {
    private val repo: WeatherAppRepository = WeatherAppRepository(
        rememberCityAndTime, getCity, getTheme, getCelsius, getRefresh, databaseMaker
    )

    private val _entries = MutableStateFlow(initialValues)
    val entries: StateFlow<List<Entry>> = _entries

    private val _city = MutableStateFlow(0)
    val city: StateFlow<Int> = _city

    private val _theme = MutableStateFlow(0)
    val theme: StateFlow<Int> = _theme

    private val _celsius = MutableStateFlow(0)
    val celsius: StateFlow<Int> = _celsius

    private val _refresh = MutableStateFlow(0L)
    val refresh: StateFlow<Long> = _refresh

    // Sets the callbacks for loading data from data source
    suspend fun setCallbacks(
        onSuccessfulCallback: () -> Unit,
        onUnsuccessfulCallback: () -> Unit,
    ) {
        onSuccessfulUpdateEntriesCallback = onSuccessfulCallback
        onUnsuccessfulUpdateEntriesCallback = onUnsuccessfulCallback
        viewModelScope.launch(Dispatchers.IO) {
            updateEntries(
                chosenCity = initChosenCity,
                callback = {
                    updateCity()
                    updateTheme()
                    updateCelsius()
                    updateRefresh()
                }
            )
        }
    }

    // Update entries from repository
    suspend fun updateEntries(
        chosenCity: Int,
        callback: suspend () -> Unit = {},
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.loadEntries(
                chosenCity = chosenCity,
                onSuccessfulCallback = onSuccessfulUpdateEntriesCallback,
                onUnsuccessfulCallback = onUnsuccessfulUpdateEntriesCallback,
                onAnyCallback = callback,
            ).collect {
                _entries.value = it
            }
        }
    }

    // Update city from SP
    suspend fun updateCity() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.loadCity().collect {
                _city.value = it
            }
        }
    }

    // Update theme choice from SP
    suspend fun updateTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.loadTheme().collect {
                _theme.value = it
            }
        }
    }

    // Update celsius/fahrenheit choice from SP
    suspend fun updateCelsius() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.loadCelsius().collect {
                _celsius.value = it
            }
        }
    }

    // Update last refresh time from SP
    suspend fun updateRefresh() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.loadRefresh().collect {
                _refresh.value = it
            }
        }
    }
}
