package com.example.kotlinrepodemo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinrepodemo.model.CommitRepoModel
import com.example.kotlinrepodemo.repository.Repository
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class KotlinRepoViewModel (val kotlinRepo: Repository) : ViewModel(){

    private var repoRecords: MutableLiveData<List<CommitRepoModel>>? = MutableLiveData()
    private var showProgress: MutableLiveData<Boolean>? = MutableLiveData()
    var compositeDisposable = CompositeDisposable()
    lateinit var disposable: Disposable


    fun getShowProgress():MutableLiveData<Boolean>?{
        return showProgress
    }

    fun getRepoRecords(){
        showProgress?.value = true

        val repoObservable: Single<List<CommitRepoModel>> = kotlinRepo.getKotlinCommits()

        compositeDisposable.add(
            repoObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({t-> repoRecords?.value = t
                    showProgress?.value = false}))
    }

    fun onShowRepoRecords() : MutableLiveData<List<CommitRepoModel>>?{
        return repoRecords
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        Log.i("OnCleared", "ViewModel destroyed")
    }

    fun showError(){
        Log.i("SHOW_ERROR", "Something Happened")
    }
}