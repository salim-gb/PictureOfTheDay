package com.example.pictureoftheday.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoonPicture(
    val moonPicture: String?,
    val title: String?,
    val bBox: String?,
    val abstractDes: String?,
) : Parcelable, ListItem