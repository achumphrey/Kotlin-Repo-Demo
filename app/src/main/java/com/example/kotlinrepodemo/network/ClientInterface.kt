package com.example.kotlinrepodemo.network


import com.example.kotlinrepodemo.common.Constants
import com.example.kotlinrepodemo.model.CommitRepoModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET


interface ClientInterface {

    @GET(Constants.END_POINT)
    fun getKotlinRepo(): Single<List<CommitRepoModel>>

}

