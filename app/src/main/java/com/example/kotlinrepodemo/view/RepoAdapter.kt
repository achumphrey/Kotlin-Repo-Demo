package com.example.kotlinrepodemo.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrepodemo.R
import com.example.kotlinrepodemo.common.inflate
import com.example.kotlinrepodemo.common.loadImage
import com.example.kotlinrepodemo.model.CommitRepoModel
import kotlinx.android.synthetic.main.cardview_recyclerview.view.*

class RepoAdapter ( val repoModel: MutableList<CommitRepoModel>):
    RecyclerView.Adapter<RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {

        val view: View = parent.inflate(R.layout.cardview_recyclerview, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repoModel.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {

        holder.tvCommitTime.text = repoModel[position].commit.author?.date
        holder.tvCommitTitle.text = repoModel[position].commit?.message
        holder.tvAuthorName.text = repoModel[position].commit.author?.name

        holder.imgAuthor.loadImage(repoModel[position].committer?.avatar_url)
    }
}

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tvCommitTitle = view.tvcommitTitle
    val tvAuthorName = view.tvAuthorName
    val tvCommitTime = view.tvCommitTime
    val imgAuthor = view.imgAuthor
}
