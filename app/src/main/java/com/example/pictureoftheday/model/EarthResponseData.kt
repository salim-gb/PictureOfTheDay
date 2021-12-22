package com.example.pictureoftheday.model

import com.google.gson.annotations.SerializedName

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
)

data class EarthResponseDataSet(
    @field:SerializedName("dataset")
    val dataSet: String?,
    @field:SerializedName("planet")
    val planet: String?,
)