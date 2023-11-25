package com.prosecshane.weatherapp.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    primary = Blue120,
    onPrimary = White,
    secondary = Blue160,
    onSecondary = White,
    tertiary = Blue160,
    onTertiary = White,
)

private val DarkColorScheme = darkColorScheme(
    background = Black,
    onBackground = White,
    surface = Black,
    onSurface = White,
    primary = Blue80,
    onPrimary = White,
    secondary = Blue40,
    onSecondary = White,
    tertiary = Blue40,
    onTertiary = White,
)

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
