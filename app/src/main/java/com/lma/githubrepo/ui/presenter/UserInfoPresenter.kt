package com.lma.githubrepo.ui.presenter

import android.util.Log
import com.lma.githubrepo.data.GithubRepository
import com.lma.githubrepo.data.GithubUserInfo
import com.lma.githubrepo.data.LMANetworkClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface UserInfoContract {
    fun getUserInfo(username: String)
    fun getUserRepositories(username: String)
}

interface UserInfoView {
    fun getUserInfo(info: GithubUserInfo)
    fun getUserRepositories(repositories: List<GithubRepository>)
}

open class UserInfoPresenter constructor(
    private val view: UserInfoView
) : UserInfoContract {

    override fun getUserInfo(username: String) {
        LMANetworkClient
            .getRetrofitClient()
            .getGithubUserInfo(username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { userInfo ->
                view.getUserInfo(userInfo)
            }
    }

    override fun getUserRepositories(username: String) {
        LMANetworkClient
            .getRetrofitClient()
            .getListRepos(username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ repositories ->
                view.getUserRepositories(repositories)
            }, {
                Log.e("LMA", "${it.localizedMessage}")
            })
    }

}