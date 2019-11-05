package com.example.kotlinrepodemo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class CommitterDetails (

	@SerializedName("avatar_url") val avatar_url : String
)