package com.example.pictureoftheday.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarsPictureData(
    @field:SerializedName("photos")
    val photos: List<MarsPhoto>?,
) : Parcelable

@Parcelize
data class MarsPhoto(
    @field:SerializedName("id")
    val id: String?,
    @field:SerializedName("sol")
    val sol: String?,
    @field:SerializedName("camera")
    val camera: CameraData?,
    @field:SerializedName("img_src")
    val url: String?,
    @field:SerializedName("earth_date")
    val date: String?,
    @field:SerializedName("rover")
    val rover: RoverData?
) : Parcelable, ListItem

@Parcelize
data class CameraData(
    @field:SerializedName("id")
    val id: String?,
    @field:SerializedName("name")
    val name: String?,
    @field:SerializedName("rover_id")
    val roverId: String?,
    @field:SerializedName("full_name")
    val fullName: String?
) : Parcelable

@Parcelize
data class RoverData(
    @field:SerializedName("id")
    val id: String?,
    @field:SerializedName("name")
    val name: String?,
    @field:SerializedName("landing_date")
    val landingDate: String?,
    @field:SerializedName("launch_date")
    val launchDate: String?,
    @field:SerializedName("status")
    val status: String?
) : Parcelable