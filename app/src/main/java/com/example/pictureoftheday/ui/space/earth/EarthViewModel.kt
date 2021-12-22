package com.example.pictureoftheday.ui.space.earth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureoftheday.model.EarthResponseData
import com.example.pictureoftheday.repository.Repository
import com.example.pictureoftheday.repository.RepositoryImpl
import com.example.pictureoftheday.util.AppState
import com.example.pictureoftheday.util.Constants.Companion.REQUEST_ERROR
import com.example.pictureoftheday.util.Constants.Companion.SERVER_ERROR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EarthViewModel(
    private val repositoryImpl: Repository = RepositoryImpl(),
    private val _response: MutableLiveData<AppState> = MutableLiveData(),
) : ViewModel() {

    private val callback = object : Callback<EarthResponseData> {

        override fun onResponse(
            call: Call<EarthResponseData>,
            response: Response<EarthResponseData>
        ) {
            val serverResponse: EarthResponseData? = response.body()
            _response.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    AppState.Success(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<EarthResponseData>, t: Throwable) {
            _response.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

    fun getData(): LiveData<AppState> = _response

    init {
        repositoryImpl.getEarthDataFromServer("2020-10-15", callback)
    }
}