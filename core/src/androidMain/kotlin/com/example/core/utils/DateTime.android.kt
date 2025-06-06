package com.example.core.utils

import java.text.SimpleDateFormat
import java.util.*

actual fun String.getFormattedDate(): String {
    val timestampFormat = "yyyy-MM-dd"
    val outputFormat = "dd/MM/yyyy"
    val local = Locale("pt", "BR")
    val dateFormatter = SimpleDateFormat(outputFormat, local)

    val parser = SimpleDateFormat(timestampFormat, local)

    try {
        val date = parser.parse(this)

        if (date != null) {
            return dateFormatter.format(date)
        }
    } catch (e: Exception) {
        // Handle parsing error
        e.printStackTrace()
    }

    // If parsing fails, return the original timestamp
    return this
}