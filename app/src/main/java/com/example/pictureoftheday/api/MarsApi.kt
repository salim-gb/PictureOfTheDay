package com.example.pictureoftheday.api

import com.example.pictureoftheday.model.MarsResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsApi {

    @GET("planetary/earth/assets?lon=-95.33&lat=29.78")
    fun getMarsPicture(
        @Query("date") date: String?,
        @Query("dim") dim: Float?,
        @Query("api_key") api: String?
    ): Call<MarsResponseData>
}