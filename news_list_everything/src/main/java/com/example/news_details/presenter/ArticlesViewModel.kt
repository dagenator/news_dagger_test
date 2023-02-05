package com.example.news_details.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.db.FavouriteDAO
import com.example.news_api.Article
import com.example.news_api.NewsService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Provider

internal class ArticlesViewModel(
    private val newsService: NewsService,
    private val favouriteDAO: FavouriteDAO
) : ViewModel() {

    val articles = flow {
        try {
            emit(newsService.everything().articles)
        } catch (e: Exception) {
            Log.d(TAG, "Error", e)
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun addToFavourite(article: Article) {
        favouriteDAO.add(article)
    }

    class Factory @Inject constructor(
        private val newsService: Provider<NewsService>,
        private val favouriteDAO: Provider<FavouriteDAO>
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == ArticlesViewModel::class.java)
            return ArticlesViewModel(newsService.get(), favouriteDAO.get()) as T
        }
    }

    companion object {

        private const val TAG = "ArticlesViewModel"


    }
}