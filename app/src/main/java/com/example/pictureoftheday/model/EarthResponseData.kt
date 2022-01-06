package com.example.pictureoftheday.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EarthResponseData(
    @field:SerializedName("date")
    val date: String?,
    @field:SerializedName("id")
    val id: String?,
    @field:SerializedName("resource")
    val dataset: EarthResponseDataSet?,
    @field:SerializedName("service_version")
    val serviceVersion: String?,
    @field:SerializedName("url")
    val url: String?
):Parcelable

@Parcelize
data class EarthResponseDataSet(
    @field:SerializedName("dataset")
    val dataSet: String?,
    @field:SerializedName("planet")
    val planet: String?,
):Parcelable