package com.example.pictureoftheday.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureoftheday.repository.PictureOfTheDayResponseData
import com.example.pictureoftheday.repository.RemoteDataSource
import com.example.pictureoftheday.repository.Repository
import com.example.pictureoftheday.repository.RepositoryImpl
import com.example.pictureoftheday.util.AppState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"

class HomeViewModel(
    private val _liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl(RemoteDataSource())
) : ViewModel() {

    fun getData(): LiveData<AppState> = _liveData

    fun sendServerRequest(date: String) {
        _liveData.value = AppState.Loading
        repositoryImpl.getDataFromServer(date, callback)
    }

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
}