package com.example.pictureoftheday.api

import com.example.pictureoftheday.model.EarthResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EarthApi {

    @GET("planetary/earth/assets?lon=-95.33&lat=29.78")
    fun getEarthPicture(
        @Query("date") ur: String?,
        @Query("dim") dim: Float?,
        @Query("api_key") api: String?
    ): Call<EarthResponseData>
}