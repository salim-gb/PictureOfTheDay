package com.example.pictureoftheday.model

import com.example.pictureoftheday.util.ListItem

data class NoteBig(
    val id: Int,
    val title: String,
    val descriptionOne: String,
    val descriptionTwo: String,
    val date: String
) : ListItem
