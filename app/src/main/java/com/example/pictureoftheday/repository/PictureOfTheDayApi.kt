package com.example.pictureoftheday.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayApi {

    @GET("planetary/apod")
    fun getPictureOfTheDay(
        @Query("date") date: String?,
        @Query("api_key") apiKey: String
    ) : Call<PictureOfTheDayResponseData>
}