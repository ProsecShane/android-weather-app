package com.prosecshane.weatherapp.ui.fragment.main.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme

@Composable
fun TopBar(
    chosenCity: Int,
    changeLocationCallback: () -> Unit = {},
    settingsCallback: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .padding(25.dp, 0.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ChangeLocationButton(
            chosenCity,
            Modifier
                .clickable { changeLocationCallback() }
                .weight(1f),
        )
        Spacer(Modifier.size(15.dp))
        SettingsButton(settingsCallback)
    }
}

@Preview
@Composable
fun TopBarPreview() {
    WeatherAppTheme {
        TopBar(3)
    }
}
