package com.prosecshane.weatherapp.compose.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.prosecshane.weatherapp.R
import com.prosecshane.weatherapp.compose.theme.Gray
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme

@Composable
fun DialogCard(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            modifier = modifier,
            border = BorderStroke(2.dp, Gray),
            content = content,
        )
    }
}

@Preview
@Composable
fun DialogCardPrevew() {
    WeatherAppTheme {
        DialogCard {
            Text(
                modifier = Modifier.padding(15.dp),
                text = stringResource(id = R.string.preview_text)
            )
        }
    }
}
