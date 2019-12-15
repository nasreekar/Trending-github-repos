package com.abhijith.assignment.github_trending.models

data class GithubRepo(
    var author: String,
    var name: String,
    var avatar: String?,
    var description: String?,
    var language: String?,
    var stars: Int?,
    var forks: Int
)