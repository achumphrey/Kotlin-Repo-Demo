package com.example.kotlinrepodemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.kotlinrepodemo.model.Author
import com.example.kotlinrepodemo.model.Commit
import com.example.kotlinrepodemo.model.CommitRepoModel
import com.example.kotlinrepodemo.model.CommitterDetails
import com.example.kotlinrepodemo.repository.Repository
import com.example.kotlinrepodemo.viewmodel.KotlinRepoViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class KotlinRepoViewModelTest {

    @Rule
    @JvmField
    var testRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository

    lateinit var kotlinRepoViewModel: KotlinRepoViewModel
    private val author = Author("name1","hello@email.com","01/01/2000")
    private val commiterDetails = CommitterDetails("www.avatar.com")
    private val commit = Commit(author,commiterDetails,"message","url")
    private val commitObserver: Observer<List<CommitRepoModel>> = mock()
    private val progressObserver: Observer<Boolean> = mock()
    private val errorObserver :Observer<String> = mock()


    @Before
    fun setup(){
        kotlinRepoViewModel = KotlinRepoViewModel(repository)
    }

    @Test
    fun getCommitsReturnsData(){
                val commits =
            mutableListOf(CommitRepoModel(commit,author,commiterDetails))

        kotlinRepoViewModel.onShowRepoRecords()?.observeForever(commitObserver)
        kotlinRepoViewModel.getShowProgress()?.observeForever(progressObserver)
        kotlinRepoViewModel.onError()?.observeForever(errorObserver)

        `when`(repository.getKotlinCommits()).thenReturn(Single.just(commits))

        kotlinRepoViewModel.getRepoRecords()

        verify(commitObserver, times(1)).onChanged(commits)
        verify(progressObserver, times(1)).onChanged(true)
        verify(errorObserver, times(0)).onChanged(ArgumentMatchers.anyString())
    }

    @Test
    fun getCommitReturnsError(){
        val errorMessage = "Exception"

        kotlinRepoViewModel.onShowRepoRecords()?.observeForever(commitObserver)
        kotlinRepoViewModel.getShowProgress()?.observeForever(progressObserver)
        kotlinRepoViewModel.onError()?.observeForever(errorObserver)

        `when`(repository.getKotlinCommits()).thenReturn(Single.error(
            RuntimeException(errorMessage)
        ))

        kotlinRepoViewModel.getRepoRecords()

        verify(commitObserver, times(0)).onChanged(ArgumentMatchers.any())
        verify(progressObserver, times(1)).onChanged(true)
        verify(errorObserver, times(1)).onChanged(errorMessage)
        verify(progressObserver, times(1)).onChanged(false)

    }
}