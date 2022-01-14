package com.example.pictureoftheday.model

data class NoteSmall(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    var isFavorite: Boolean = false
) : ListItem
