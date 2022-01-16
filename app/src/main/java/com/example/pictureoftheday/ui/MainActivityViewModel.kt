package com.example.pictureoftheday.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    var data: Boolean = false

    //for demonstration
    fun dataLoading(): Boolean {
        viewModelScope.launch {
            delay(1000)
            data = true
        }
        return data
    }
}