package com.example.pictureoftheday.ui.space.mars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureoftheday.model.MarsPictureData
import com.example.pictureoftheday.repository.Repository
import com.example.pictureoftheday.repository.RepositoryImpl
import com.example.pictureoftheday.util.AppState
import com.example.pictureoftheday.util.Constants
import com.example.pictureoftheday.util.DateHelperImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarsViewModel(
    private val repositoryImpl: Repository = RepositoryImpl(),
    private val _response: MutableLiveData<AppState> = MutableLiveData(),
) : ViewModel() {

    private val _selectedDate: MutableLiveData<String> = MutableLiveData()
    private val selectedDate: LiveData<String>
        get() = _selectedDate

    private val callback = object : Callback<MarsPictureData> {

        override fun onResponse(
            call: Call<MarsPictureData>,
            response: Response<MarsPictureData>
        ) {
            val serverResponse: MarsPictureData? = response.body()

            _response.postValue(
                if (response.isSuccessful && serverResponse != null) {
                        AppState.Success(serverResponse)
                } else {
                    AppState.Error(Throwable(Constants.SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<MarsPictureData>, t: Throwable) {
            _response.postValue(AppState.Error(Throwable(t.message ?: Constants.REQUEST_ERROR)))
        }
    }

    fun getData(): LiveData<AppState> = _response

    fun sendRequestToServer(date: String) {
        repositoryImpl.getMarsDataFromServer(date, callback)
    }

    fun onDateChange(date: String) {
        _selectedDate.value = date
        sendRequestToServer(date)
    }
}