package com.prosecshane.weatherapp.util.model

// Model that can contain either string or icon
data class StringIcon(
    val useString: Boolean,
    val string: String = "",
    val icon: Int = 0,
)
