package com.example.kotlinrepodemo.dagger

import com.example.kotlinrepodemo.network.ClientInterface
import com.example.kotlinrepodemo.repository.Repository
import com.example.kotlinrepodemo.repository.RepositoryImpl
import com.example.kotlinrepodemo.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(repository: Repository): ViewModelFactory{
        return ViewModelFactory(repository)
    }

    @Provides
    @Singleton
    fun provideRepository(clientInterface: ClientInterface): Repository{
        return RepositoryImpl(clientInterface)
    }
}