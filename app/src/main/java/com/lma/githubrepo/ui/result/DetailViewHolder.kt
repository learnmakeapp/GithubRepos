package com.lma.githubrepo.ui.result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.lma.githubrepo.R
import com.lma.githubrepo.data.GithubRepository

class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val stars = itemView.findViewById<TextView>(R.id.tx_stars)
    private val repositoryName = itemView.findViewById<TextView>(R.id.tx_repository_name)
    private val description = itemView.findViewById<TextView>(R.id.tx_description)

    fun bind(repository: GithubRepository) {
        stars.text = repository.startsCount.toString()
        repositoryName.text = repository.name

        if (!repository.description.isNullOrEmpty()) {
            description.text = repository.description.trim()
        } else {
            description.visibility = View.GONE
        }
    }

    companion object {
        @LayoutRes val LAYOUT = R.layout.detail_item_repository

        fun inflate(parent: ViewGroup): DetailViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(LAYOUT, parent, false)

            return DetailViewHolder(view)
        }
    }

}