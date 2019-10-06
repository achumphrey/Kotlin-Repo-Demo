package com.example.kotlinrepodemo.dagger

import android.app.Application
import com.example.kotlinrepodemo.repository.Repository
import com.example.kotlinrepodemo.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTeamViewModelFactory(repository: Repository): ViewModelFactory{
        return ViewModelFactory(repository)
    }
}