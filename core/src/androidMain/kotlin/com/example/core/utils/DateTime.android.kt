package com.example.core.utils

import java.text.SimpleDateFormat
import java.util.Locale

actual object DateTime {
    actual fun getFormattedDate(timestamp: String): String {
        val timestampFormat = "yyyy-MM-dd"
        val outputFormat = "dd MMMM yyyy"
        val local = Locale("pt", "BR")

        val dateFormatter = SimpleDateFormat(outputFormat, local)

        val parser = SimpleDateFormat(timestampFormat, local)

        try {
            val date = parser.parse(timestamp)
            if (date != null) {
                return dateFormatter.format(date)
            }
        } catch (e: Exception) {
            // Handle parsing error
            e.printStackTrace()
        }

        // If parsing fails, return the original timestamp
        return timestamp
    }
}