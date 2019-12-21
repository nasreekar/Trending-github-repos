package com.abhijith.assignment.github_trending

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhijith.assignment.github_trending.adapter.TrendingRepoListAdapter
import com.abhijith.assignment.github_trending.models.GithubRepo
import com.abhijith.assignment.github_trending.network.ServiceGenerator
import com.abhijith.assignment.github_trending.viewmodels.TrendingListViewModel
import kotlinx.android.synthetic.main.activity_trending_list.*


class TrendingListActivity : BaseActivity() {

    companion object {
        val TAG = TrendingListActivity::class.java.simpleName
    }

    private lateinit var trendingListViewModel: TrendingListViewModel
    private lateinit var adapter: TrendingRepoListAdapter
    private lateinit var trendingRepoList: List<GithubRepo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trending_list)

        setSupportActionBar(findViewById(R.id.dashboard_toolbar))

        ServiceGenerator(this)

        adapter = TrendingRepoListAdapter(this)
        rv_repo.adapter = adapter
        rv_repo.layoutManager = LinearLayoutManager(this)
        rv_repo.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        // Get the view model
        trendingListViewModel = ViewModelProviders.of(this).get(TrendingListViewModel::class.java)

        fetchTrendingRepos()

        swipeContainer.setOnRefreshListener {
            fetchTrendingRepos()
        }

        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

    }

    private fun fetchTrendingRepos() {
        trendingListViewModel.getRepos()?.observe(this, Observer { repoList ->
            Log.i(TAG, "Viewmodel response: $repoList")

            repoList?.let {
                // stop animating Shimmer and hide the layout
                shimmer_view_container.stopShimmer()
                shimmer_view_container.visibility = View.GONE
                if (it.isNotEmpty()) {
                    empty_list.visibility = View.GONE
                    trendingRepoList = it
                    adapter.clear()
                    adapter.setRepos(trendingRepoList.sortedByDescending { repo -> repo.stars })
                } else {
                    empty_list.visibility = View.VISIBLE
                }
                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.isRefreshing = false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.itemId

        if (id == R.id.sortByStars) {
            adapter.setRepos(trendingRepoList.sortedByDescending { repo -> repo.stars })
            return true
        }
        if (id == R.id.sortByName) {
            adapter.setRepos(trendingRepoList.sortedBy { repo -> repo.name })
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        shimmer_view_container.startShimmer()
    }

    override fun onPause() {
        shimmer_view_container.stopShimmer()
        super.onPause()
    }
}
