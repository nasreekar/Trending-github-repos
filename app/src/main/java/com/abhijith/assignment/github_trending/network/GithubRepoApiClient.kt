package com.abhijith.assignment.github_trending.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.abhijith.assignment.github_trending.models.GithubRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GithubRepoApiClient {

    companion object {
        const val TAG = "GithubRepoApiClient"
    }

    private var repos = MutableLiveData<List<GithubRepo>>()

    fun getTrendingRepos(): MutableLiveData<List<GithubRepo>> {
        ServiceGenerator.instance.getGithubRepoApi().getTrendingRepos()
            .enqueue(object : Callback<List<GithubRepo>> {
                override fun onResponse(
                    call: Call<List<GithubRepo>>?,
                    response: Response<List<GithubRepo>>?
                ) {

                    val githubRepos = response?.body()

                    if (githubRepos != null) {
                        repos.value = githubRepos
                    }
                }

                override fun onFailure(call: Call<List<GithubRepo>>, t: Throwable) {
                    Log.d(TAG, "data failure" + t.message)
                }
            })
        return repos
    }
}