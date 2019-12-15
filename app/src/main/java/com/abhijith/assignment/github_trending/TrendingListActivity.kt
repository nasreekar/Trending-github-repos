package com.abhijith.assignment.github_trending

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.abhijith.assignment.github_trending.models.GithubRepo
import com.abhijith.assignment.github_trending.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class TrendingListActivity : BaseActivity() {

    companion object {
        val TAG = TrendingListActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trending_list)

        setSupportActionBar(findViewById(R.id.dashboard_toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.itemId

        if (id == R.id.sortByStars) {
            Toast.makeText(this, "Sort By Stars Clicked", Toast.LENGTH_SHORT).show()
            return true
        }
        if (id == R.id.sortByName) {
            Toast.makeText(this, "Sort By Name Clicked", Toast.LENGTH_SHORT).show()
            testRetrofitRequest()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun testRetrofitRequest() {
        val githubRepoApi = ServiceGenerator.getGithubRepoApi()

        val responeCall = githubRepoApi.getTrendingRepos()
        responeCall.enqueue(object : Callback<List<GithubRepo>> {
            override fun onFailure(call: Call<List<GithubRepo>>, t: Throwable) {
                Log.d(TAG, "Server Response: $t")
            }

            override fun onResponse(
                call: Call<List<GithubRepo>>,
                response: Response<List<GithubRepo>>
            ) {
                Log.d(TAG, "Server Response: $response")
                if (response.code() == 200) {
                    Log.d(TAG, "Server Response: ${response.body().toString()}")
                    val repoList = response.body()
                    if (repoList != null) {
                        for (repo in repoList) {
                            Log.d(TAG, "Github Repo name: ${repo.author}")
                        }
                    }

                } else {
                    try {
                        Log.d(TAG, "Server Response: ${response.errorBody().toString()}")
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }
}
