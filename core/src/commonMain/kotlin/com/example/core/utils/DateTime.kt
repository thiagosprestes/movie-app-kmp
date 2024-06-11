package com.example.core.utils

expect object DateTime {
    fun getFormattedDate(
        timestamp: String,
    ): String
}
