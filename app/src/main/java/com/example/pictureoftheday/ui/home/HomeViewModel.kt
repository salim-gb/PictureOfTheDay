package com.example.pictureoftheday.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureoftheday.model.PictureOfTheDayResponseData
import com.example.pictureoftheday.repository.Repository
import com.example.pictureoftheday.repository.RepositoryImpl
import com.example.pictureoftheday.util.AppState
import com.example.pictureoftheday.util.Constants.Companion.REQUEST_ERROR
import com.example.pictureoftheday.util.Constants.Companion.SERVER_ERROR
import com.example.pictureoftheday.util.DateHelperImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    private val _liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl(),
    private val dateHelperImpl: DateHelperImpl = DateHelperImpl()
) : ViewModel() {

    private val _selectedDate: MutableLiveData<String> = MutableLiveData()
    val liveData: LiveData<AppState>
        get() = _liveData

    val selectedDate: LiveData<String>
        get() = _selectedDate

    private val callback = object : Callback<PictureOfTheDayResponseData> {
        override fun onResponse(
            call: Call<PictureOfTheDayResponseData>,
            response: Response<PictureOfTheDayResponseData>
        ) {
            val serverResponse: PictureOfTheDayResponseData? = response.body()
            _liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    AppState.Success(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
            _liveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

    fun getData(): LiveData<AppState> = _liveData

    private fun sendServerRequest() {
        _liveData.value = AppState.Loading
        _selectedDate.value?.let {
            repositoryImpl.getPictureOfTheDayFromServer(it, callback)
        }
    }

    fun onDateChange(date: String) {
        _selectedDate.value = date
        sendServerRequest()
    }

    init {
        _selectedDate.value = dateHelperImpl.today
        sendServerRequest()
    }
}