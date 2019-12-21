package com.abhijith.assignment.github_trending.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abhijith.assignment.github_trending.database.AppDatabase
import com.abhijith.assignment.github_trending.models.GithubRepo
import com.abhijith.assignment.github_trending.repositories.GithubRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


// holding, retrieving and displaying Repo's
class TrendingListViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: GithubRepository
    var repos: MutableLiveData<List<GithubRepo>> = MutableLiveData()
    private val mDisposables = CompositeDisposable()

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val githubRepoDao = AppDatabase.getInstance(application).githubRepoDao()
        repository = GithubRepository(application.baseContext, githubRepoDao)
    }

    fun getRepos(): LiveData<List<GithubRepo>> {
        val observable: Observable<List<GithubRepo>> = repository.getAllTrendingRepos()
        val result = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                repos.postValue(result)
            }, { error ->
                error.printStackTrace()
            }, {
                //completed
            })
        mDisposables.add(result)
        return repos
    }

    override fun onCleared() {
        super.onCleared()
        mDisposables.clear() //no more leaks. It takes care of lifecycle for you
    }
}