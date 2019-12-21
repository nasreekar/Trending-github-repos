package com.abhijith.assignment.github_trending.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abhijith.assignment.github_trending.models.GithubRepo
import io.reactivex.Flowable

@Dao
interface GithubRepoDao {
    @Query("SELECT * FROM githubrepo")
    fun queryAllRepos(): Flowable<List<GithubRepo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRepo(repo: GithubRepo)

    @Query("DELETE FROM githubrepo")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRepos(repos: List<GithubRepo>)
}