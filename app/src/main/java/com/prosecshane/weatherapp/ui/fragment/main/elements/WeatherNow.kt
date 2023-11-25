package com.prosecshane.weatherapp.ui.fragment.main.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prosecshane.weatherapp.R
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme
import com.prosecshane.weatherapp.data.model.Entry
import com.prosecshane.weatherapp.data.model.Implementation
import com.prosecshane.weatherapp.data.model.WeatherStatus
import com.prosecshane.weatherapp.util.formatStatus
import com.prosecshane.weatherapp.util.formatTemperature

@Composable
fun WeatherNow(
    entry: Entry,
    celsius: Int = 0,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        val formattedStatus = formatStatus(entry.status)
        Icon(
            painter = painterResource(id = formattedStatus.icon),
            contentDescription = "Today's Weather Description",
            modifier = Modifier
                .size(150.dp)
                .weight(1f)
                .padding(15.dp),
            tint = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            text = formattedStatus.text,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            text = formatTemperature(entry.temperature, celsius == 0),
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Spacer(Modifier.size(15.dp))
    }
}

@Preview
@Composable
fun WeatherNowPreview() {
    WeatherAppTheme {
        Surface(color = MaterialTheme.colorScheme.primary) {
            WeatherNow(
                entry = Entry(
                    25f,
                    1_065_704_400_549L,
                    WeatherStatus.Clear,
                    Implementation.Now
                )
            )
        }
    }
}
