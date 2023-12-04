package com.prosecshane.weatherapp.ui.fragment.main.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme
import com.prosecshane.weatherapp.data.model.Entry
import com.prosecshane.weatherapp.data.model.Implementation
import com.prosecshane.weatherapp.data.model.WeatherStatus

// Section that has 3 coming weather entries
@Composable
fun WeatherClose(
    entries: List<Entry>,
    celsius: Int = 0,
) {
    val sortedEntries = entries.sortedBy { x -> x.time }
    Column(modifier = Modifier.fillMaxWidth()) {
        for (i in 0 until 3) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(MaterialTheme.colorScheme.background),
            )
            WeatherHour(
                sortedEntries[i],
                celsius
            )
        }
    }
}

@Preview
@Composable
fun WeatherClosePreview() {
    WeatherAppTheme {
        Surface(color = MaterialTheme.colorScheme.primary) {
            WeatherClose(
                listOf(
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
                    )
                )
            )
        }
    }
}
