package com.example.kotlinrepodemo.model

import com.google.gson.annotations.SerializedName

data class Tree (

	@SerializedName("sha") val sha : String,
	@SerializedName("url") val url : String
)