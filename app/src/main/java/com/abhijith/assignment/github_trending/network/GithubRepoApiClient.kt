package com.abhijith.assignment.github_trending.network

import com.abhijith.assignment.github_trending.models.GithubRepo
import io.reactivex.Observable


class GithubRepoApiClient {

    companion object {
        const val TAG = "GithubRepoApiClient"
    }

    fun getTrendingRepos(): Observable<List<GithubRepo>> {
        return ServiceGenerator.instance.getGithubRepoApi().getTrendingRepos()
    }
}