package com.example.core.favourite.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.news_api.Article

@Dao
interface FavouriteDAO {
    @Query("SELECT * FROM article")
    fun getAll(): List<Article>

    @Query("SELECT url FROM article")
    fun getAllUrls(): List<String>

    @Insert
    fun add(vararg article: Article)

    @Query("DELETE FROM article WHERE url = :url")
    fun deleteByUrl(url: String)



}