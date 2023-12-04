package com.prosecshane.weatherapp.ui.fragment.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prosecshane.weatherapp.compose.elements.InAppNotification
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme
import com.prosecshane.weatherapp.data.model.Entry
import com.prosecshane.weatherapp.data.model.Implementation
import com.prosecshane.weatherapp.data.model.WeatherStatus
import com.prosecshane.weatherapp.ui.fragment.main.elements.ForecastRow
import com.prosecshane.weatherapp.ui.fragment.main.elements.RefreshBar
import com.prosecshane.weatherapp.ui.fragment.main.elements.TopBar
import com.prosecshane.weatherapp.ui.fragment.main.elements.WeatherTodayCard

// Main fragment of app, contains the weather info and buttons
@Composable
fun MainFragment(
    entries: List<Entry>,
    chosenCity: Int,
    celsius: Int,
    refreshTime: Long,
    changeLocationCallback: () -> Unit = {},
    settingsCallback: () -> Unit = {},
    refreshCallback: () -> Unit = {},
) {
    Column (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
    ) {
        Spacer(Modifier.size(15.dp))
        TopBar(
            chosenCity,
            changeLocationCallback,
            settingsCallback,
        )
        Spacer(Modifier.size(5.dp))
        RefreshBar(refreshTime) { refreshCallback() }
        Spacer(Modifier.size(5.dp))
        WeatherTodayCard(
            entries = entries.filter {
                    x -> ((x.implementation == Implementation.Now) ||
                          (x.implementation == Implementation.Close))
            },
            modifier = Modifier
                .weight(1f)
                .padding(25.dp, 0.dp)
                .fillMaxWidth(),
            celsius = celsius,
        )
        Spacer(Modifier.size(15.dp))
        ForecastRow(
            entries.filter { x -> x.implementation == Implementation.Forecast },
            celsius,
        )
        Spacer(Modifier.size(25.dp))
    }
}

@Preview
@Composable
fun MainFragmentPreview() {
    WeatherAppTheme {
        MainFragment(
            listOf(
                Entry(25f, 1_065_704_400_549L, WeatherStatus.Clear, Implementation.Now),
                Entry(25f, 1_065_704_400_549L, WeatherStatus.Clear, Implementation.Close),
                Entry(25f, 1_065_704_400_549L, WeatherStatus.Clear, Implementation.Close),
                Entry(25f, 1_065_704_400_549L, WeatherStatus.Clear, Implementation.Close),
                Entry(25f, 1_065_704_400_549L, WeatherStatus.Clear, Implementation.Forecast),
                Entry(25f, 1_065_704_400_549L, WeatherStatus.Clear, Implementation.Forecast),
                Entry(25f, 1_065_704_400_549L, WeatherStatus.Clear, Implementation.Forecast),
            ),
            3,
            0,
            System.currentTimeMillis(),
        )
        InAppNotification(
            text = "Нет подключения к Интернету!",
            onDismissCallback = {},
        )
    }
}
