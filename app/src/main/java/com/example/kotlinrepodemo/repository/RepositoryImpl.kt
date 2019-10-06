package com.example.kotlinrepodemo.repository

import android.app.Application
import com.example.kotlinrepodemo.model.CommitRepoModel
import com.example.kotlinrepodemo.network.ClientInterface
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val clientInterface: ClientInterface) :Repository{
    override fun getKotlinCommits(): Single<List<CommitRepoModel>> {
        return clientInterface.getKotlinRepo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}