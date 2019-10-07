package com.example.kotlinrepodemo.dagger


import com.example.kotlinrepodemo.view.RepoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class, RepositoryModule::class))
interface AppComponent {

    fun inject (repoFragment: RepoFragment)
}