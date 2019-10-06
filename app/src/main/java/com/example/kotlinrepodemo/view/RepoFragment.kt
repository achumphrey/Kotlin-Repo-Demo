package com.example.kotlinrepodemo.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.kotlinrepodemo.R
import com.example.kotlinrepodemo.dagger.DaggerNetworkComponent
import com.example.kotlinrepodemo.dagger.NetworkModule
import com.example.kotlinrepodemo.dagger.RepositoryModule
import com.example.kotlinrepodemo.model.CommitRepoModel
import com.example.kotlinrepodemo.viewmodel.KotlinRepoViewModel
import com.example.kotlinrepodemo.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.repo_fragment.*
import javax.inject.Inject

class RepoFragment : Fragment() {

    @Inject
    lateinit var repoModelViewFactory: ViewModelFactory
    private lateinit var viewModel: KotlinRepoViewModel
    private lateinit var adapter: RepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.repo_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerNetworkComponent.builder()
            .networkModule(NetworkModule(activity!!.application))
            .repositoryModule(RepositoryModule())
            .build()
            .inject(this)

        viewModel = ViewModelProviders.of(this,repoModelViewFactory).get(KotlinRepoViewModel::class.java)

        val displayProgress : MutableLiveData<Boolean>? = viewModel.getShowProgress()

        viewModel.getRepoRecords()

        displayProgress?.observe(this, object : Observer<Boolean> {
            override fun onChanged(t: Boolean?) {
                if (t == false)
                    prgs_bar.visibility = View.GONE
                else
                    prgs_bar.visibility = View.VISIBLE
            }
        })


        viewModel.onShowRepoRecords()?.observe(this, Observer {

            adapter.repoModel.addAll(it)
            adapter.notifyDataSetChanged()
        })

        setUpRecyclerView()
    }

    private  fun setUpRecyclerView(){
        adapter = RepoAdapter(mutableListOf())
        rv_list.layoutManager = LinearLayoutManager(context)
        rv_list.adapter = adapter
    }

}
