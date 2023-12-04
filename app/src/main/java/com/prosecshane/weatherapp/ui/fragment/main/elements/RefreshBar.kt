package com.prosecshane.weatherapp.ui.fragment.main.elements

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prosecshane.weatherapp.R
import com.prosecshane.weatherapp.compose.elements.PrimaryCard
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme
import com.prosecshane.weatherapp.util.formatUpdateTime
import kotlinx.coroutines.delay

// Top part where everything concerning the last refresh is located
@Composable
fun RefreshBar(
    lastUpdated: Long,
    refreshCallback: () -> Unit = {},
) {
    val lastUpdatedState: MutableState<String> =
        remember { mutableStateOf("...") }
    LaunchedEffect(lastUpdated) {
       while (true) {
           delay(1000L)
           lastUpdatedState.value = formatUpdateTime(lastUpdated)
           Log.d("WeatherAppDebug", "$lastUpdated")
       }
    }
    Row(
        modifier = Modifier
            .padding(25.dp, 0.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .padding(15.dp)
                .weight(1f),
            text = "Обновлено в последний раз:\n${lastUpdatedState.value}",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 12.sp,
            lineHeight = 14.sp,
        )
        Spacer(Modifier.size(15.dp))
        PrimaryCard(
            modifier = Modifier
                .clickable { refreshCallback() }
        ) {
            Icon(
                modifier = Modifier.padding(15.dp),
                painter = painterResource(id = R.drawable.refresh),
                contentDescription = "Refresh Button",
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Preview
@Composable
fun RefreshBarPreview() {
    WeatherAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            RefreshBar(System.currentTimeMillis())
        }
    }
}
