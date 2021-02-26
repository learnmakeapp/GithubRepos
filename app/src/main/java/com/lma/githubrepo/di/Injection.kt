package com.lma.githubrepo.di

import com.lma.githubrepo.data.LMANetworkClient
import com.lma.githubrepo.data.LMAService

object Injection {
    fun provideNetworkService(): LMAService {
        return LMANetworkClient.getRetrofitClient()
    }
}