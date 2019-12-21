package com.abhijith.assignment.github_trending

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhijith.assignment.github_trending.adapter.TrendingRepoListAdapter
import com.abhijith.assignment.github_trending.network.ServiceGenerator
import com.abhijith.assignment.github_trending.viewmodels.TrendingListViewModel
import kotlinx.android.synthetic.main.activity_trending_list.*


class TrendingListActivity : BaseActivity() {

    companion object {
        val TAG = TrendingListActivity::class.java.simpleName
    }

    private lateinit var trendingListViewModel: TrendingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trending_list)

        setSupportActionBar(findViewById(R.id.dashboard_toolbar))

        ServiceGenerator(this)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_repo)
        val adapter = TrendingRepoListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get the view model
        trendingListViewModel = ViewModelProviders.of(this).get(TrendingListViewModel::class.java)
        // Observe the model
        trendingListViewModel.getRepos()?.observe(this, Observer { repoList ->
            Log.i(TAG, "Viewmodel response: $repoList")

            repoList?.let {
                // stop animating Shimmer and hide the layout
                adapter.setRepos(it)
                shimmer_view_container.stopShimmer()
                shimmer_view_container.visibility = View.GONE
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
            Toast.makeText(this, "Sort By Stars Clicked", Toast.LENGTH_SHORT).show()
            return true
        }
        if (id == R.id.sortByName) {
            Toast.makeText(this, "Sort By Name Clicked", Toast.LENGTH_SHORT).show()
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
