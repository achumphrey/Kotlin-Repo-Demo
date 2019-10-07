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
    private var showError: MutableLiveData<String>? = MutableLiveData()
    var compositeDisposable = CompositeDisposable()


    fun getRepoRecords(){

        val repoObservable: Single<List<CommitRepoModel>> = kotlinRepo.getKotlinCommits()

        compositeDisposable.add(
            repoObservable
                .doOnSubscribe {showProgress?.postValue(true) }
                .doOnError {
                    showProgress?.value = false
                }
                .subscribe({t-> repoRecords?.value = t
                    showProgress?.value = false}, {
                    showError?.value = it.message.toString()
                }))
    }

    fun onShowRepoRecords() : MutableLiveData<List<CommitRepoModel>>?{
        return repoRecords
    }

    fun getShowProgress():MutableLiveData<Boolean>?{
        return showProgress
    }

    fun onError():MutableLiveData<String>?{
        return showError
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        Log.i("OnCleared", "ViewModel destroyed")
    }


}