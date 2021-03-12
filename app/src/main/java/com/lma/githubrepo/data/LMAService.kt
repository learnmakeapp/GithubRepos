package com.lma.githubrepo.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface LMAService {
    @GET("/users/{username}/repos")
    fun getListRepos(
        @Path("username") username: String
    ): Single<List<GithubRepository>>

    @GET("/users/{username}")
    fun getGithubUserInfo(
        @Path("username") username: String
    ): Single<GithubUserInfo>
}