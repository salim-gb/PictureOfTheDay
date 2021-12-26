package com.example.pictureoftheday.ui.space

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureoftheday.model.EarthResponseData
import com.example.pictureoftheday.model.MarsPictureData
import com.example.pictureoftheday.repository.Repository
import com.example.pictureoftheday.repository.RepositoryImpl
import com.example.pictureoftheday.util.AppState
import com.example.pictureoftheday.util.Constants
import com.example.pictureoftheday.util.Constants.Companion.SERVER_ERROR
import com.example.pictureoftheday.util.DateHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class SpaceSharedViewModel(
    private val repositoryImpl: Repository = RepositoryImpl(),
    private val dateHelperImpl: DateHelper = DateHelper()
) : ViewModel() {

    private val _earthResponse: MutableLiveData<AppState> = MutableLiveData()
    private val _marsResponse: MutableLiveData<AppState> = MutableLiveData()

    private val _isChipEarthPictureTodayChecked = MutableLiveData<Boolean>()
    val isChipEarthPictureTodayChecked: LiveData<Boolean>
        get() = _isChipEarthPictureTodayChecked

    fun getEarthData(): LiveData<AppState> = _earthResponse

    fun getMarsData(): LiveData<AppState> = _marsResponse

    private val earthCallback = object : Callback<EarthResponseData> {

        override fun onResponse(
            call: Call<EarthResponseData>,
            response: Response<EarthResponseData>
        ) {
            val serverResponse: EarthResponseData? = response.body()
            _earthResponse.postValue(
                if (response.isSuccessful && serverResponse == null) {
                    AppState.Success(response.message())
                } else if (response.isSuccessful && serverResponse != null) {
                    AppState.Success(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<EarthResponseData>, t: Throwable) {
            _earthResponse.postValue(
                AppState.Error(
                    Throwable(
                        t.message ?: Constants.REQUEST_ERROR
                    )
                )
            )
        }
    }

    private val marsCallback = object : Callback<MarsPictureData> {

        override fun onResponse(call: Call<MarsPictureData>, response: Response<MarsPictureData>) {
            val serverResponse: MarsPictureData? = response.body()
            _marsResponse.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    AppState.Success(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<MarsPictureData>, t: Throwable) {
            _marsResponse.postValue(AppState.Error(Throwable(t.message ?: Constants.REQUEST_ERROR)))
        }
    }

    fun sendEarthRequestToServer(date: String) {
        repositoryImpl.getEarthDataFromServer(date, earthCallback)
    }

    fun sendMarsRequestToServer(date: String) {
        repositoryImpl.getMarsDataFromServer(date, marsCallback)
    }

    fun onDateChange(date: Long, item: Int) {
        dateHelperImpl.dayLong(date).also {
            when (item) {
                0 -> sendEarthRequestToServer(it)
                1 -> sendMarsRequestToServer(it)
            }
        }
    }

    fun onChipEarthPictureTodayClick() {
        _isChipEarthPictureTodayChecked.value = true

        dateHelperImpl.dayString(0).also {
            sendEarthRequestToServer(it)
        }
    }
}

