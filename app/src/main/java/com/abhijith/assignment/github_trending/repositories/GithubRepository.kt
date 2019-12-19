package com.abhijith.assignment.github_trending.repositories

import androidx.lifecycle.MutableLiveData
import com.abhijith.assignment.github_trending.models.GithubRepo
import com.abhijith.assignment.github_trending.network.GithubRepoApiClient

class GithubRepository {
    // The ViewModel maintains a reference to the repository to get data.
    private var githubRepoApiClient: GithubRepoApiClient = GithubRepoApiClient()

    fun getTrendingRepos(): MutableLiveData<List<GithubRepo>>? {
        return githubRepoApiClient.getTrendingRepos()
    }

    companion object {
        private var INSTANCE: GithubRepository? = null
        fun getInstance() = INSTANCE
            ?: GithubRepository().also {
                INSTANCE = it
            }
    }

}