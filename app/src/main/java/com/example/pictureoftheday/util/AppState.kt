package com.example.pictureoftheday.util

import com.example.pictureoftheday.repository.PictureOfTheDayResponseData

sealed class AppState {
    data class Success(val pictureOfTheDayResponseData: PictureOfTheDayResponseData) : AppState()
    object Loading : AppState()
    data class Error(val error: Throwable) : AppState()
}