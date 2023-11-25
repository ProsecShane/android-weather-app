package com.prosecshane.weatherapp.compose.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prosecshane.weatherapp.R
import com.prosecshane.weatherapp.compose.theme.Gray
import com.prosecshane.weatherapp.compose.theme.WeatherAppTheme
import com.prosecshane.weatherapp.util.formatUpdateTime
import com.prosecshane.weatherapp.util.model.StringIcon

@Composable
fun CustomSingleChoice(
    amount: Int,
    choiceState: State<Int>,
    values: List<StringIcon>,
    onClickCallback: (Int) -> Unit = {},
) {
    if (amount != values.size) {
        throw Exception("CustomSingleChoice: amount and length of values List must be equal")
    }
    if (choiceState.value >= amount) {
        throw Exception("CustomSingleChoice: choiceState value must be lower than amount")
    }

    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (i in 0 until amount) {
            if (i > 0) Spacer(Modifier.size(10.dp))
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .clickable {
                        onClickCallback(i)
                    }
                    .weight(1f),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                border = BorderStroke(
                    2.dp,
                    if (i == choiceState.value) Gray else Color.Transparent
                ),
            ) {
                if (values[i].useString) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        textAlign = TextAlign.Center,
                        text = values[i].string,
                        color = if (i == choiceState.value)
                            MaterialTheme.colorScheme.onBackground
                        else Color.Gray,
                    )
                } else {
                    Icon(
                        painter = painterResource(id = values[i].icon),
                        contentDescription = "Custom Single Choice Icon",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        tint = if (i == choiceState.value)
                            MaterialTheme.colorScheme.onBackground
                        else Color.Gray,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomSingleChoicePreview() {
    WeatherAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            CustomSingleChoice(
                amount = 3,
                choiceState = remember { mutableStateOf(0) },
                values = listOf(
                    StringIcon(true, string = "Auto"),
                    StringIcon(false, icon = R.drawable.clear_day),
                    StringIcon(false, icon = R.drawable.clear_night),
                )
            )
        }
    }
}
