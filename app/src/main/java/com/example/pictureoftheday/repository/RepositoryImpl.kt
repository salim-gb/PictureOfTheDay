package com.example.pictureoftheday.repository

import retrofit2.Callback

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    Repository {
    override fun getDataFromServer(date: String, callback: Callback<PictureOfTheDayResponseData>) {
        remoteDataSource.getPictureOfTheDay(date, callback)
    }
}