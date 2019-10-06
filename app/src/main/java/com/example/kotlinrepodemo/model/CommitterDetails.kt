package com.example.kotlinrepodemo.model

import com.google.gson.annotations.SerializedName

data class CommitterDetails (

	@SerializedName("avatar_url") val avatar_url : String
)