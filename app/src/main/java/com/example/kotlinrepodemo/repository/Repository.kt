package com.example.kotlinrepodemo.repository

import android.app.Application
import com.example.kotlinrepodemo.model.CommitRepoModel
import com.example.kotlinrepodemo.network.ClientInterface
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class Repository @Inject constructor(val clientInterface: ClientInterface) {

    fun getKotlinCommits(): Single<List<CommitRepoModel>> {
        return clientInterface.getKotlinRepo()

    }
}