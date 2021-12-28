package com.example.pictureoftheday.util

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {

    val dayString: (Int) -> String = {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .format(
                Calendar.getInstance().apply {
                    add(Calendar.DATE, it)
                }.time
            )
    }

    val dayLong: (Long) -> String = {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .format(it)
    }
}