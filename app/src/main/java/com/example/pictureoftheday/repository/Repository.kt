package com.example.pictureoftheday.repository

import retrofit2.Callback

interface Repository {
    fun getDataFromServer(
        date: String,
        callback: Callback<PictureOfTheDayResponseData>
    )
}