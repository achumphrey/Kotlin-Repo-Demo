package com.example.kotlinrepodemo.dagger


import com.example.kotlinrepodemo.view.RepoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class, RepositoryModule::class))
interface NetworkComponent {

    fun inject (repoFragment: RepoFragment)
}