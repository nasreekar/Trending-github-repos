package com.abhijith.assignment.github_trending.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abhijith.assignment.github_trending.models.GithubRepo
import com.abhijith.assignment.github_trending.util.Constants

@Database(entities = [GithubRepo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun githubRepoDao(): GithubRepoDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, Constants.DATABASE_NAME
                    )
                        .build()
                }
            }
            return INSTANCE as AppDatabase
        }
    }
}