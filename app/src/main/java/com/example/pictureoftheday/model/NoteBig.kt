package com.example.pictureoftheday.model

import androidx.annotation.DrawableRes

data class NoteBig(
    val id: Int,
    @DrawableRes
    val image: Int?,
    val title: String,
    val descriptionOne: String,
    val descriptionTwo: String,
    val date: String,
    var isFavorite: Boolean = false
) : ListItem
