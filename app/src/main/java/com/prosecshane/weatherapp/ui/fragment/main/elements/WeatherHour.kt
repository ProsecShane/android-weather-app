package com.prosecshane.weatherapp.ui.fragment.main.elements

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme
import com.prosecshane.weatherapp.data.model.Entry
import com.prosecshane.weatherapp.data.model.Implementation
import com.prosecshane.weatherapp.data.model.WeatherStatus
import com.prosecshane.weatherapp.util.formatStatus
import com.prosecshane.weatherapp.util.formatTemperature
import com.prosecshane.weatherapp.util.formatTime

// An object containing a close weather entry
@Composable
fun WeatherHour(
    entry: Entry,
    celsius: Int = 0,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
    ) {
        Spacer(Modifier.size(15.dp))
        Text(
            modifier = Modifier
                .padding(0.dp, 15.dp)
                .weight(23f),
            text = formatTime(entry.time),
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            modifier = Modifier
                .padding(0.dp, 15.dp)
                .weight(18f),
            text = "${formatStatus(entry.status, entry.time).text} | ",
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.End,
        )
        Text(
            modifier = Modifier
                .padding(0.dp, 15.dp)
                .weight(9f),
            text = formatTemperature(entry.temperature, celsius == 0),
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Spacer(Modifier.size(15.dp))
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
                    WeatherStatus.Overcast,
                    Implementation.Close
                )
            )
        }
    }
}
