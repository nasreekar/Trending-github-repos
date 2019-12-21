package com.abhijith.assignment.github_trending.network

import com.abhijith.assignment.github_trending.models.GithubRepo
import com.abhijith.assignment.github_trending.util.Constants
import io.reactivex.Observable
import retrofit2.http.GET

interface GithubRepoApi {
    @GET(value = Constants.ENDPOINT)
    fun getTrendingRepos(): Observable<List<GithubRepo>>
}