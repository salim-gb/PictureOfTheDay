package com.example.pictureoftheday.model

import com.example.pictureoftheday.util.ListItem

data class NoteSmall(
    val id: Int,
    val title: String,
    val description: String,
    val date: String
) : ListItem
