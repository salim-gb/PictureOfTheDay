package com.example.pictureoftheday.api

import com.example.pictureoftheday.util.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .build()
    }

    val pictureOfTheDayApi: PictureOfTheDayApi by lazy {
        retrofit.create(PictureOfTheDayApi::class.java)
    }

    val earthApi: EarthApi by lazy {
        retrofit.create(EarthApi::class.java)
    }

    val marsApi: MarsApi by lazy {
        retrofit.create(MarsApi::class.java)
    }
}