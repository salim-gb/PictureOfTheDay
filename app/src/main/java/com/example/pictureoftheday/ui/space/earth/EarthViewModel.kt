package com.example.pictureoftheday.ui.space.earth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureoftheday.model.EarthResponseData
import com.example.pictureoftheday.repository.Repository
import com.example.pictureoftheday.repository.RepositoryImpl
import com.example.pictureoftheday.util.AppState
import com.example.pictureoftheday.util.Constants.Companion.EMPTY_REQUEST
import com.example.pictureoftheday.util.Constants.Companion.REQUEST_ERROR
import com.example.pictureoftheday.util.Constants.Companion.SERVER_ERROR
import com.example.pictureoftheday.util.DateHelperImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class EarthViewModel(
    private val repositoryImpl: Repository = RepositoryImpl(),
    private val _response: MutableLiveData<AppState> = MutableLiveData(),
    private val dateHelperImpl: DateHelperImpl = DateHelperImpl()
) : ViewModel() {

    private val _isChipChecked = MutableLiveData<Boolean>()
    val isChipChecked: LiveData<Boolean>
        get() = _isChipChecked

    private val callback = object : Callback<EarthResponseData> {

        override fun onResponse(
            call: Call<EarthResponseData>,
            response: Response<EarthResponseData>
        ) {
            val serverResponse: EarthResponseData? = response.body()

            _response.postValue(
                if (serverResponse == null) {
                    AppState.Success(EMPTY_REQUEST)
                } else {
                    if (response.isSuccessful) {
                        AppState.Success(serverResponse)
                    } else {
                        AppState.Error(Throwable(SERVER_ERROR))
                    }
                }
            )
        }

        override fun onFailure(call: Call<EarthResponseData>, t: Throwable) {
            Timber.i("Error : ${t.message}")
            _response.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

    fun onDateChange(date: String) {
        _isChipChecked.value = false
        sendRequestToServer(date)
    }

    fun getData(): LiveData<AppState> = _response

    fun onChipEarthPictureToday() {
        _isChipChecked.value = true
        sendRequestToServer(dateHelperImpl.today)
    }

    private fun sendRequestToServer(date: String) {
        repositoryImpl.getEarthDataFromServer(date, callback)
    }

    init {
        _isChipChecked.value = true
        sendRequestToServer(dateHelperImpl.today)
    }
}