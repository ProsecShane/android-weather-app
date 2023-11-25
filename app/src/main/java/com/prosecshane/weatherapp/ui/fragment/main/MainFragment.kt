package com.prosecshane.weatherapp.ui.fragment.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prosecshane.weatherapp.R
import com.prosecshane.weatherapp.compose.elements.InAppNotification
import com.prosecshane.weatherapp.compose.elements.PrimaryCard
import com.prosecshane.weatherapp.compose.theme.Gray
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme
import com.prosecshane.weatherapp.data.model.Entry
import com.prosecshane.weatherapp.data.model.Implementation
import com.prosecshane.weatherapp.data.model.WeatherStatus
import com.prosecshane.weatherapp.ui.fragment.main.elements.ForecastRow
import com.prosecshane.weatherapp.ui.fragment.main.elements.RefreshBar
import com.prosecshane.weatherapp.ui.fragment.main.elements.TopBar
import com.prosecshane.weatherapp.ui.fragment.main.elements.WeatherTodayCard

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
        RefreshBar(refreshTime) { refreshCallback() }
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
