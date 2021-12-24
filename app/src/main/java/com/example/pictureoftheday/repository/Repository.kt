package com.example.pictureoftheday.repository

import com.example.pictureoftheday.model.EarthResponseData
import com.example.pictureoftheday.model.MarsPictureData
import com.example.pictureoftheday.model.PictureOfTheDayResponseData
import retrofit2.Callback

interface Repository {
    fun getPictureOfTheDayFromServer(
        date: String,
        callback: Callback<PictureOfTheDayResponseData>
    )

    fun getEarthDataFromServer(
        date: String,
        callback: Callback<EarthResponseData>
    )

    fun getMarsDataFromServer(
        date: String,
        callback: Callback<MarsPictureData>
    )
}