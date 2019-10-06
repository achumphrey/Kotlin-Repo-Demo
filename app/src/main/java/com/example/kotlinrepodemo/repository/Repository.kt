package com.example.kotlinrepodemo.repository

import com.example.kotlinrepodemo.model.CommitRepoModel
import io.reactivex.Single


interface Repository {
    fun getKotlinCommits(): Single<List<CommitRepoModel>>
}