package com.example.pictureoftheday.api

import com.example.pictureoftheday.model.PictureOfTheDayResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayApi {

    @GET("planetary/apod")
    fun getPictureOfTheDayRequest(
        @Query("date") date: String?,
        @Query("api_key") apiKey: String
    ) : Call<PictureOfTheDayResponseData>
}