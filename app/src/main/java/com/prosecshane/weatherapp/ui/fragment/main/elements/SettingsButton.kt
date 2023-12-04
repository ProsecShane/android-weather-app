package com.prosecshane.weatherapp.ui.fragment.main.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prosecshane.weatherapp.R
import com.prosecshane.weatherapp.compose.elements.PrimaryCard
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme

// Button that creates a Settings Dialog
@Composable
fun SettingsButton(
    clickCallback: () -> Unit = {},
) {
    PrimaryCard(
        modifier = Modifier.clickable { clickCallback() }
    ) {
        Icon(
            modifier = Modifier.padding(15.dp),
            painter = painterResource(id = R.drawable.baseline_settings_24),
            contentDescription = "Settings",
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Preview
@Composable
fun SettingsButtonPreview() {
    WeatherAppTheme {
        SettingsButton()
    }
}
