package com.example.core.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.news_api.Article

@Dao
interface FavouriteDAO {
    @Query("SELECT * FROM article")
    fun getAll(): List<Article>

    @Insert
    fun add(vararg article: Article)

    @Query("DELETE WHERE id = :id")
    fun deleteById(id: Int)

}