package com.example.news_api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@[Serializable]
data class Articles(
  val articles: List<Article>
)

@[Serializable Entity]
data class Article(

  val author: String?,
  val title: String?,
  val description: String?,
  @PrimaryKey
  val url: String,
  @ColumnInfo(name = "url_to_img")
  val urlToImage: String?,
  val content: String?
){
}