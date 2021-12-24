package com.example.pictureoftheday.api

import com.example.pictureoftheday.model.MarsPictureData
import com.example.pictureoftheday.ui.space.mars.Mars
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsApi {
    @GET("mars-photos/api/v1/rovers/curiosity/photos?")
    fun getMarsPicture(
        @Query("earth_date") date: String?,
        @Query("api_key") api: String?
    ): Call<MarsPictureData>
}