package com.prosecshane.weatherapp.ui.fragment.location

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.prosecshane.weatherapp.R
import com.prosecshane.weatherapp.compose.elements.DialogCard
import com.prosecshane.weatherapp.compose.theme.Gray
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme
import com.prosecshane.weatherapp.data.cityNamesRussian

@Composable
fun LocationDialog(
    cityNames: List<String>,
    chosenCity: Int,
    onCityChosen: (Int) -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    DialogCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 25.dp),
        onDismissRequest = onDismissRequest,
    ) {
        val locationScroll = remember { ScrollState(0) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
                .verticalScroll(locationScroll),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Location Back Button",
                modifier = Modifier
                    .clickable { onDismissRequest() }
                    .padding(10.dp),
                tint = MaterialTheme.colorScheme.onBackground,
            )

            for (i in cityNames.indices) {
                if (i > 0) Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Gray),
                )
                LocationElement(
                    cityNames[i],
                    i == chosenCity,
                ) {
                    onCityChosen(i)
                    onDismissRequest()
                }
            }
        }
    }
}

@Preview
@Composable
fun LocationDialogPreview() {
    WeatherAppTheme {
        LocationDialog(cityNamesRussian, 3)
    }
}
