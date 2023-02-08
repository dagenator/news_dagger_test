package com.example.core.favourite.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news_api.Article

@Database(entities = [Article::class], version = 1)
abstract class FavouriteDB : RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDAO
}