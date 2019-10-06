package com.example.kotlinrepodemo.model

import com.google.gson.annotations.SerializedName

data class CommitRepoModel (

	@SerializedName("commit") val commit : Commit,
	@SerializedName("author") val author : Author,
	@SerializedName("committer") val committer : CommitterDetails
)