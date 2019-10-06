package com.example.kotlinrepodemo.model

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("name")val name: String,
    @SerializedName("email")val email: String,
    @SerializedName("date")val date: String
)
