package com.prosecshane.weatherapp.ui.fragment.main.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme
import com.prosecshane.weatherapp.data.model.Entry
import com.prosecshane.weatherapp.data.model.Implementation
import com.prosecshane.weatherapp.data.model.WeatherStatus
import com.prosecshane.weatherapp.util.formatStatus
import com.prosecshane.weatherapp.util.formatTime
import com.prosecshane.weatherapp.util.formatTemperature

@Composable
fun WeatherHour(
    entry: Entry,
    celsius: Int = 0,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .padding(15.dp)
                .weight(1f),
            text = formatTime(entry.time),
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            modifier = Modifier.padding(15.dp),
            text = "${
                formatTemperature(entry.temperature, celsius == 0)
            } • ${
                formatStatus(entry.status).text
            }",
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Preview
@Composable
fun WeatherHourPreview() {
    WeatherAppTheme {
        Surface(color = MaterialTheme.colorScheme.primary) {
            WeatherHour(
                Entry(
                    25f,
                    System.currentTimeMillis(),
                    WeatherStatus.Clear,
                    Implementation.Close
                )
            )
        }
    }
}
