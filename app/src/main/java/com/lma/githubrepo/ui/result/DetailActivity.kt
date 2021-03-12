package com.lma.githubrepo.ui.result

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lma.githubrepo.R
import com.lma.githubrepo.data.GithubRepository
import com.lma.githubrepo.data.GithubUserInfo
import com.lma.githubrepo.ui.presenter.UserInfoPresenter
import com.lma.githubrepo.ui.presenter.UserInfoView

class DetailActivity : AppCompatActivity(), UserInfoView {

    private val presenter by lazy { UserInfoPresenter(this) }
    private val adapter by lazy { DetailAdapter() }

    private var username: String = ""

    private lateinit var txRepositoryTitle: TextView
    private lateinit var rvRepositories: RecyclerView

    private lateinit var ivAvatar: ImageView
    private lateinit var txFullname: TextView
    private lateinit var txUsername: TextView
    private lateinit var txBiodata: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        username = intent?.getStringExtra(PARAM_USERNAME).orEmpty()
        presenter.getUserInfo(username)
        presenter.getUserRepositories(username)
    }

    override fun getUserInfo(info: GithubUserInfo) {
        Toast.makeText(this, info.name, Toast.LENGTH_SHORT).show()

        ivAvatar = findViewById(R.id.iv_avatar)
        txFullname = findViewById(R.id.tx_fullname)
        txUsername = findViewById(R.id.tx_username)
        txBiodata = findViewById(R.id.tx_biodata)

        txFullname.text = info.name
        txUsername.text = "@${info.login}"
        txBiodata.text = info.bio

        Glide.with(this)
            .asBitmap()
            .circleCrop()
            .load(info.avatarUrl)
            .into(ivAvatar)
    }

    override fun getUserRepositories(repositories: List<GithubRepository>) {
        if (repositories.isNotEmpty()) {
            rvRepositories = findViewById(R.id.rv_repositories)
            txRepositoryTitle = findViewById(R.id.tx_repository_title)

            txRepositoryTitle.visibility = View.VISIBLE

            rvRepositories.layoutManager = LinearLayoutManager(this)
            rvRepositories.adapter = adapter

            adapter.setData(repositories)
        }
    }

    companion object {
        private const val PARAM_USERNAME = "username"

        fun route(context: Context, username: String) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(PARAM_USERNAME, username)
            context.startActivity(intent)
        }
    }
}