package com.example.kotlinrepodemo.view

import com.example.kotlinrepodemo.model.CommitRepoModel


interface RepoClickListener {
    fun onClick(repoModel: CommitRepoModel)
}
