package com.abhijith.assignment.github_trending.repositories

import android.content.Context
import android.util.Log
import com.abhijith.assignment.github_trending.database.GithubRepoDao
import com.abhijith.assignment.github_trending.models.GithubRepo
import com.abhijith.assignment.github_trending.network.GithubRepoApiClient
import com.abhijith.assignment.github_trending.util.ConnectivityUtil
import io.reactivex.Observable


class GithubRepository(private val context: Context, private val githubRepoDao: GithubRepoDao) {

    companion object {
        const val TAG = "GithubRepository"
    }

    // The ViewModel maintains a reference to the repository to get data.
    private var githubRepoApiClient: GithubRepoApiClient = GithubRepoApiClient()

    fun getAllTrendingRepos(): Observable<List<GithubRepo>> {
        val hasConnection = ConnectivityUtil.isNetworkAvailable(context)
        var observableFromApi: Observable<List<GithubRepo>>? = null
        if (hasConnection) {
            observableFromApi = getTrendingReposFromApi()
        }
        val observableFromDb = getTrendingReposFromDb()

        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
        else observableFromDb
    }

    private fun getTrendingReposFromApi(): Observable<List<GithubRepo>> {
        return githubRepoApiClient.getTrendingRepos().doOnNext {
            githubRepoDao.deleteAll()
            for (item in it) {
                githubRepoDao.insertRepo(item)
            }
        }
    }

    private fun getTrendingReposFromDb(): Observable<List<GithubRepo>> {
        return githubRepoDao.queryAllRepos()
            .toObservable()
            .doOnNext {
                Log.i(TAG, it.size.toString())
            }
    }
}