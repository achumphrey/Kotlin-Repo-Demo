package com.example.kotlinrepodemo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.example.kotlinrepodemo.R
import com.example.kotlinrepodemo.model.CommitRepoModel
import kotlinx.android.synthetic.main.dialog_repo_name.*


class RepoNameDialog : DialogFragment() {
    companion object {
        const val ARG_REPO = "arg_repo"
        fun instance(repoModel: CommitRepoModel): RepoNameDialog {
            val cakeDetailDialog = RepoNameDialog()
            val arguments = Bundle()
            arguments.putString(ARG_REPO, repoModel.commit.author?.name)
            cakeDetailDialog.arguments = arguments
            return cakeDetailDialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_repo_name, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val authorName = arguments?.getString(ARG_REPO)
            authorName?.apply {
            tvRepoName.text = authorName
        }
        btnOk.setOnClickListener { dismiss() }

    }
}