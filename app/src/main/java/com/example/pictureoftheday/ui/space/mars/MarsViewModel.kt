package com.example.pictureoftheday.ui.space.mars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureoftheday.model.MarsResponseData
import com.example.pictureoftheday.repository.Repository
import com.example.pictureoftheday.repository.RepositoryImpl
import com.example.pictureoftheday.util.AppState
import com.example.pictureoftheday.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarsViewModel(
    private val repositoryImpl: Repository = RepositoryImpl(),
    private val _response: MutableLiveData<AppState> = MutableLiveData(),
) : ViewModel() {

    private val callback = object : Callback<MarsResponseData> {

        override fun onResponse(
            call: Call<MarsResponseData>,
            response: Response<MarsResponseData>
        ) {
            val serverResponse: MarsResponseData? = response.body()
            _response.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    AppState.Success(serverResponse)
                } else {
                    AppState.Error(Throwable(Constants.SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<MarsResponseData>, t: Throwable) {
            _response.postValue(AppState.Error(Throwable(t.message ?: Constants.REQUEST_ERROR)))
        }
    }

    fun getData(): LiveData<AppState> = _response

    init {
        repositoryImpl.getMarsDataFromServer("2020-10-15", callback)
    }
}