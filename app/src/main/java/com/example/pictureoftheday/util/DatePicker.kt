package com.example.pictureoftheday.util

import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker

object DatePicker {
    private val constraintsBuilder =
        CalendarConstraints.Builder()
            .setValidator(DateValidatorPointBackward.now())

    val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Select Date")
        .setCalendarConstraints(constraintsBuilder.build())
        .build()
}