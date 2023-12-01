package com.prosecshane.weatherapp.ui.fragment.main.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prosecshane.weatherapp.compose.elements.PrimaryCard
import com.prosecshane.weatherapp.compose.theme.Gray
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme
import com.prosecshane.weatherapp.data.model.Entry
import com.prosecshane.weatherapp.data.model.Implementation
import com.prosecshane.weatherapp.data.model.WeatherStatus
import com.prosecshane.weatherapp.util.formatForecastTime
import com.prosecshane.weatherapp.util.formatStatus
import com.prosecshane.weatherapp.util.formatTemperature
import com.prosecshane.weatherapp.util.formatTime

@Composable
fun ForecastCard(
    entry: Entry,
    celsius: Int = 0,
    modifier: Modifier = Modifier,
) {
    PrimaryCard(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val formattedStatus = formatStatus(entry.status, 43200000L)
            Spacer(Modifier.size(2.dp))
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = formattedStatus.icon),
                contentDescription = "Forecast Card Icon",
                tint = MaterialTheme.colorScheme.onPrimary,
            )
            Spacer(Modifier.size(5.dp))
            Text(
                text = formatTemperature(entry.temperature, celsius == 0),
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Text(
                modifier = Modifier.padding(0.dp, 5.dp),
                text = formattedStatus.text,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 12.sp,
            )
            Text(
                text = formatForecastTime(entry.time),
                color = Gray,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.size(5.dp))
        }
    }
}

@Preview
@Composable
fun ForecastCardPreview() {
    WeatherAppTheme {
        ForecastCard(
            Entry(
                25f,
                1_065_704_400_549L,
                WeatherStatus.Overcast,
                Implementation.Forecast
            )
        )
    }
}
