package com.example.kotlinrepodemo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class CommitRepoModel (

	@SerializedName("commit") val commit : Commit,
	@SerializedName("author") val author : Author,
	@SerializedName("committer") val committer : CommitterDetails
)