package com.example.pictureoftheday.util

import java.text.SimpleDateFormat
import java.util.*

class DateHelperImpl : DateHelper {
    private val c = Calendar.getInstance()
    val today = getFormatDate(c.timeInMillis)
    val yesterday = getFormatDate(c.timeInMillis - 1 * 24 * 60 * 60 * 1000)
    val beforeYesterday = getFormatDate(c.timeInMillis - 2 * 24 * 60 * 60 * 1000)

    override fun getFormatDate(date: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return sdf.format(date)
    }
}