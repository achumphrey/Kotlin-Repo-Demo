package com.example.kotlinrepodemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinrepodemo.repository.Repository

class ViewModelFactory (private val repository: Repository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KotlinRepoViewModel(repository) as T
    }
}


