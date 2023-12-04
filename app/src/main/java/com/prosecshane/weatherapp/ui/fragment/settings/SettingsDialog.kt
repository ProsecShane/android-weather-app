package com.prosecshane.weatherapp.ui.fragment.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prosecshane.weatherapp.R
import com.prosecshane.weatherapp.compose.elements.CustomSingleChoice
import com.prosecshane.weatherapp.compose.elements.DialogCard
import com.prosecshane.weatherapp.compose.theme.Gray
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme
import com.prosecshane.weatherapp.util.model.StringIcon

// Dialog with app settings
@Composable
fun SettingsDialog(
    themeChoice: State<Int>,
    celsius: State<Int>,
    onThemeClickCallback: (Int) -> Unit = {},
    onCelsiusClickCallback: (Int) -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    DialogCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 25.dp),
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Settings Back Button",
                modifier = Modifier
                    .clickable { onDismissRequest() }
                    .padding(10.dp),
                tint = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(Modifier.size(10.dp))

            Text(
                modifier = Modifier.padding(10.dp, 0.dp),
                text = "Тема",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            CustomSingleChoice(
                amount = 3,
                choiceState = themeChoice,
                values = listOf(
                    StringIcon(true, string = "Авто"),
                    StringIcon(false, icon = R.drawable.clear_day),
                    StringIcon(false, icon = R.drawable.clear_night),
                ),
                onClickCallback = onThemeClickCallback,
            )

            Box(modifier = Modifier.fillMaxWidth().height(2.dp).background(Gray))
            Spacer(Modifier.size(10.dp))

            Text(
                modifier = Modifier.padding(10.dp, 0.dp),
                text = "Ед. измерения",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            CustomSingleChoice(
                amount = 2,
                choiceState = celsius,
                values = listOf(
                    StringIcon(true, string = "°C"),
                    StringIcon(true, string = "°F"),
                ),
                onClickCallback = onCelsiusClickCallback,
            )
        }
    }
}

@Preview
@Composable
fun SettingsDialogPreview() {
    WeatherAppTheme {
        SettingsDialog(
            remember { mutableStateOf(0) },
            remember { mutableStateOf(0) },
        )
    }
}
