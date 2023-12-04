package com.prosecshane.weatherapp.ui.fragment.main.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.prosecshane.weatherapp.compose.elements.PrimaryCard
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme
import com.prosecshane.weatherapp.data.model.Entry
import com.prosecshane.weatherapp.data.model.Implementation
import com.prosecshane.weatherapp.data.model.WeatherStatus

// An object containing current and coming weather
@Composable
fun WeatherTodayCard(
    entries: List<Entry>,
    celsius: Int = 0,
    modifier: Modifier = Modifier,
) {
    PrimaryCard(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            WeatherNow(
                entry = entries.filter { x -> x.implementation == Implementation.Now }[0],
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                celsius = celsius,
            )
            WeatherClose(
                entries.filter { x -> x.implementation == Implementation.Close },
                celsius,
            )
        }
    }
}

@Preview
@Composable
fun WeatherTodayCardPreview() {
    WeatherAppTheme {
        WeatherTodayCard(
            listOf(
                Entry(
                    25f,
                    1_065_704_400_549L,
                    WeatherStatus.Clear,
                    Implementation.Now
                ),
                Entry(
                    25f,
                    1_065_704_400_549L,
                    WeatherStatus.Clear,
                    Implementation.Close
                ),
                Entry(
                    25f,
                    1_065_704_400_549L,
                    WeatherStatus.Clear,
                    Implementation.Close
                ),
                Entry(
                    25f,
                    1_065_704_400_549L,
                    WeatherStatus.Clear,
                    Implementation.Close
                ),
            ),
        )
    }
}
