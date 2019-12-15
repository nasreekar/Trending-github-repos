package com.abhijith.assignment.github_trending.network

import com.abhijith.assignment.github_trending.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private val retroFitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = retroFitBuilder.build()

    private val githubRepoApi = retrofit.create(GithubRepoApi::class.java)

    fun getGithubRepoApi(): GithubRepoApi {
        return githubRepoApi
    }
}