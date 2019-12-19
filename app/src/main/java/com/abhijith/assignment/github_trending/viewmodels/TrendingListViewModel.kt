package com.abhijith.assignment.github_trending.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhijith.assignment.github_trending.models.GithubRepo
import com.abhijith.assignment.github_trending.repositories.GithubRepository


// holding, retrieving and displaying Repo's
class TrendingListViewModel : ViewModel() {

    private var githubRepository: GithubRepository = GithubRepository()

    fun getRepos(): MutableLiveData<List<GithubRepo>>? {
        return githubRepository.getTrendingRepos()
    }
}