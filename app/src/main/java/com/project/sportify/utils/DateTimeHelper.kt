package com.project.sportify.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateTimeHelper {
    const val DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_FORMAT_DD_M_YYYY = "dd-MM-yyyy"
    const val DATE_FORMAT_DD_MM_YYYY_2 = "dd-MM-yyyy"
    const val DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy"
    const val DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd"
    const val DATE_FORMAT_DD_MMM_YYYY = "dd MMM. YYYY"
    const val DATE_FORMAT_HH_MMA_DD_MMM_YY = "dd MMM yy"

    fun convertDateStringToAnotherFormat(
        dateString: String?,
        dateParserFormat: String = DATE_FORMAT_DD_M_YYYY,
        dateFormatter: String = DATE_FORMAT_DD_MM_YYYY,
        desiredLocale: Locale = Locale.getDefault(),
        alternateValue: String = Constants.General.DASH_TEXT
    ): String {
        if (dateString.isNullOrEmpty()) {
            return alternateValue
        }
        val parser = SimpleDateFormat(dateParserFormat, desiredLocale)
        val formatter = SimpleDateFormat(dateFormatter, desiredLocale)
        return  try {
            val parsedDate = parser.parse(dateString) ?: return alternateValue
            formatter.format(parsedDate)
        } catch (e: ParseException) {
            e.printStackTrace()
            alternateValue
        }
    }

    fun convertLongToDateFormat(
        time: Long?,
        dateFormatter: String = DATE_FORMAT_DD_MM_YYYY,
        desiredLocale: Locale = Locale.getDefault(),
        alternateValue: String = Constants.General.DASH_TEXT
    ): String {
        if (time == null) {
            return alternateValue
        }
        val date = Date(time)
        val formatter = SimpleDateFormat(dateFormatter, desiredLocale)
        try {
            return formatter.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return alternateValue
    }

    fun convertDateToDateFormat(
        date: Date?,
        dateFormatter: String = DATE_FORMAT_DD_MM_YYYY,
        desiredLocale: Locale = Locale.getDefault(),
        alternateValue: String = Constants.General.DASH_TEXT
    ): String {
        if (date == null) {
            return alternateValue
        }
        val formatter = SimpleDateFormat(dateFormatter, desiredLocale)
        try {
            return formatter.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return alternateValue
    }

    fun convertLocalDateToDateFormat(
        date: LocalDate?,
        dateFormatter: String = DATE_FORMAT_DD_MM_YYYY,
        desiredLocale: Locale = Locale.getDefault(),
        alternateValue: String = Constants.General.EMPTY_TEXT
    ): String {
        if (date == null) {
            return alternateValue
        }
        val formatter = SimpleDateFormat(dateFormatter, desiredLocale)
        try {
            return formatter.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return alternateValue
    }

    fun convertStringToDateFormat(
        dateValue: String?,
        defaultValue: Date,
        dateFormatter: String = DATE_FORMAT_DD_MM_YYYY,
        desiredLocale: Locale = Locale.getDefault(),
    ): Date {
        if (dateValue == null) {
            return defaultValue
        }
        val formatter = SimpleDateFormat(dateFormatter, desiredLocale)
        try {
            val date = formatter.parse(dateValue)
            if (date != null) {
                val calendar = Calendar.getInstance().apply {
                    time = date
                }
                calendar.add(Calendar.DATE, 1)
                return calendar.time
            }
            return defaultValue
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return defaultValue
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertStringToLocalDateFormat(
        dateValue: String?,
        defaultValue: LocalDate,
        dateFormatter: String = DATE_FORMAT_DD_MM_YYYY,
        desiredLocale: Locale = Locale.getDefault(),
    ): LocalDate {
        if (dateValue.isNullOrEmpty()) {
            return defaultValue
        }
        val formatter = DateTimeFormatter.ofPattern(dateFormatter).apply {
            withLocale(desiredLocale)
        }
        try {
            return LocalDate.parse(dateValue, formatter)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return defaultValue
    }
}