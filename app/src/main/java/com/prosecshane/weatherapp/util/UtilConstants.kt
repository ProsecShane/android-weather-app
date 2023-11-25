package com.prosecshane.weatherapp.util

import com.prosecshane.weatherapp.R
import com.prosecshane.weatherapp.data.model.WeatherStatus
import java.text.SimpleDateFormat
import java.util.Locale

val fullFormatter = SimpleDateFormat("HH:mm, dd ", Locale.UK)
val simpleFormatter = SimpleDateFormat("HH:mm", Locale.US)

const val dayInMillis = 86400000L
const val tenHoursInMillis = 36000000L
const val minuteInMillis = 60000L

val monthsRU = mapOf(
    1L to "янв", 2L to "фев", 3L to "мар",
    4L to "апр", 5L to "май", 6L to "июн",
    7L to "июл", 8L to "авг", 9L to "сен",
    10L to "окт", 11L to "ноя", 12L to "дек",
)

val statusFormatString = mapOf(
    WeatherStatus.Clear to "Ясно",
    WeatherStatus.Cloudy to "Облачно",
    WeatherStatus.Overcast to "Пасмурно",
    WeatherStatus.Raining to "Дождь",
    WeatherStatus.Thunder to "Гроза",
    WeatherStatus.Snowing to "Снег",
    WeatherStatus.Mist to "Туман",
)

val statusFormatIcon = mapOf(
    WeatherStatus.Clear to R.drawable.clear_day,
    WeatherStatus.Cloudy to R.drawable.cloudy_day,
    WeatherStatus.Overcast to R.drawable.overcast,
    WeatherStatus.Raining to R.drawable.raining,
    WeatherStatus.Thunder to R.drawable.thunder,
    WeatherStatus.Snowing to R.drawable.snowing,
    WeatherStatus.Mist to R.drawable.mist,
)

val dayIconToNightIcon = mapOf(
    R.drawable.clear_day to R.drawable.clear_night,
    R.drawable.cloudy_day to R.drawable.cloudy_night,
)
