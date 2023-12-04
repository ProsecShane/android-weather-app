package com.prosecshane.weatherapp.compose.elements

import androidx.compose.foundation.layout.ColumnScope
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
import com.prosecshane.weatherapp.R
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme

// Custom Card object, styled for this App
@Composable
fun PrimaryCard(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(20.dp),
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier,
        content = content,
    )
}

@Preview
@Composable
fun PrimaryCardPreview() {
    WeatherAppTheme {
        PrimaryCard {
            Text(
                modifier = Modifier.padding(15.dp),
                text = stringResource(id = R.string.preview_text)
            )
        }
    }
}
