package com.abhijith.assignment.github_trending

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class TrendingListActivity : BaseActivity() {

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
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
