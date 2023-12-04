package com.prosecshane.weatherapp.ui.fragment.main.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prosecshane.weatherapp.R
import com.prosecshane.weatherapp.compose.elements.PrimaryCard
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme
import com.prosecshane.weatherapp.data.cityNamesRussian

// Button that creates a Choose Location dialog, that allows changing the location
@Composable
fun ChangeLocationButton(
    chosenCity: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary
) {
    PrimaryCard(
        modifier = modifier
    ) {
        Row {
            Text(
                modifier = Modifier
                    .padding(15.dp)
                    .weight(1f),
                text = cityNamesRussian[chosenCity],
                color = color,
            )
            Icon(
                modifier = Modifier.padding(15.dp),
                painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                contentDescription = "Choose Location Icon",
                tint = color,
            )
        }
    }
}

@Preview
@Composable
fun ChangeLocationButtonPreview() {
    WeatherAppTheme {
        ChangeLocationButton(3)
    }
}
