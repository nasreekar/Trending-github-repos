package com.abhijith.assignment.github_trending.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GithubRepo(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var author: String,
    var name: String,
    var avatar: String?,
    var description: String?,
    var language: String?,
    var stars: Int?,
    var forks: Int
)