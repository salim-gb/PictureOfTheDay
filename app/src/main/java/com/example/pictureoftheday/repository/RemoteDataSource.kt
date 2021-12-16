package com.example.pictureoftheday.repository

import com.example.pictureoftheday.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    private val baseUrl = "https://api.nasa.gov/"

    private val api = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(PictureOfTheDayApi::class.java)

    fun getPictureOfTheDay(date: String, callback: Callback<PictureOfTheDayResponseData>) {
        api.getPictureOfTheDay(date, BuildConfig.NASA_API_KEY).enqueue(callback)
    }
}