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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme
import com.prosecshane.weatherapp.data.model.Entry
import com.prosecshane.weatherapp.data.model.Implementation
import com.prosecshane.weatherapp.data.model.WeatherStatus

// A row of three forecast cards with forecast entries
@Composable
fun ForecastRow(
    entries: List<Entry>,
    celsius: Int = 0,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp, 0.dp)
            .height(intrinsicSize = IntrinsicSize.Max),
    ) {
        for (i in 0 until 3) {
            ForecastCard(
                entries[i],
                celsius,
                Modifier.weight(1f)
            )
            if (i < 2) Spacer(modifier = Modifier.size(15.dp))
        }
    }
}

@Preview
@Composable
fun ForecastRowPreview() {
    WeatherAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ForecastRow(
                listOf(
                    Entry(25f, 1_065_704_400_549L, WeatherStatus.Clear, Implementation.Forecast),
                    Entry(25f, 1_065_704_400_549L, WeatherStatus.Clear, Implementation.Forecast),
                    Entry(25f, 1_065_704_400_549L, WeatherStatus.Clear, Implementation.Forecast),
                )
            )
        }
    }
}
