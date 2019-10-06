package com.example.kotlinrepodemo.model

import com.google.gson.annotations.SerializedName

data class Commit (

	@SerializedName("author") val author : Author,
	@SerializedName("committer") val committer : CommitterDetails,
	@SerializedName("message") val message : String,
	@SerializedName("url") val url : String
)