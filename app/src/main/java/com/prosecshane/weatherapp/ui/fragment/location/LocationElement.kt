package com.prosecshane.weatherapp.ui.fragment.location

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.prosecshane.weatherapp.R
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme

@Composable
fun LocationElement(
    cityName: String,
    checked: Boolean = false,
    callback: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .clickable { callback() }
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = cityName,
            color = MaterialTheme.colorScheme.onBackground,
        )
        if (checked) {
            Icon(
                painter = painterResource(id = R.drawable.checkmark),
                contentDescription = "Location Icon, Checked or Not",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(20.dp),
            )
        }
    }
}

@Preview
@Composable
fun LocationElementPreview() {
    WeatherAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LocationElement("Пермь", true)
        }
    }
}
