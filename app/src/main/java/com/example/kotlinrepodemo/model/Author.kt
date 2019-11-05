package com.example.kotlinrepodemo.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class Author(
    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("email")
    @Expose
    val email: String,

    @SerializedName("date")
    @Expose
    val date: String

)
