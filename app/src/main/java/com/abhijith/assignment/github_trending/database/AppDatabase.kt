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
        private var instance: AppDatabase? = null
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, Constants.DATABASE_NAME
                )
                    .build()
            }
            return instance as AppDatabase
        }

        fun destroyInstance() {
            instance = null
        }
    }
}