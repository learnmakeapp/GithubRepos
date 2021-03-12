package com.lma.githubrepo.ui.result

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lma.githubrepo.data.GithubRepository

class DetailAdapter : RecyclerView.Adapter<DetailViewHolder>() {

    private var repositories: List<GithubRepository> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    override fun getItemCount() = repositories.size

    fun setData(data: List<GithubRepository>) {
        repositories = data
        notifyDataSetChanged()
    }

}