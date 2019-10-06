package com.example.kotlinrepodemo

import com.example.kotlinrepodemo.model.Author
import com.example.kotlinrepodemo.model.Commit
import com.example.kotlinrepodemo.model.CommitRepoModel
import com.example.kotlinrepodemo.model.CommitterDetails
import com.example.kotlinrepodemo.repository.Repository
import com.example.kotlinrepodemo.viewmodel.KotlinRepoViewModel
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.lenient
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class KotlinRepoViewModelTest {

    @Mock
    lateinit var repository: Repository

    lateinit var kotlinRepoViewModel: KotlinRepoViewModel
    private val author = Author("name1","hello@email.com","01/01/2000")
    private val commiterDetails = CommitterDetails("www.avatar.com")
    private val commit = Commit(author,commiterDetails,"message","url")


    @Before
    fun setup(){
        kotlinRepoViewModel = KotlinRepoViewModel(repository)
    }

    @Test
    fun getCommitsReturnsData(){
        val commits =
            mutableListOf(CommitRepoModel(commit,author,commiterDetails)
                ,CommitRepoModel(commit,author,commiterDetails))
        `when`(repository.getKotlinCommits()).thenReturn(Single.just(commits))
    }
}