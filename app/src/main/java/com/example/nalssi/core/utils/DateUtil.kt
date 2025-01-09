package com.example.nalssi.core.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtil {
    private const val DEFAULT_PATTERN = "yyyy-MM-dd HH:mm"

    fun stringToDate(dateString: String, pattern: String = DEFAULT_PATTERN): Date? {
        return try {
            val formatter = SimpleDateFormat(pattern, Locale.getDefault())
            formatter.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun dateToString(date: Date, pattern: String = DEFAULT_PATTERN): String {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(date)
    }

    fun getDiffInDays(startDate: Date, endDate: Date): Int {
        return ((startDate.time - endDate.time) / (1000 * 60 * 60 * 24)).toInt()
    }
}