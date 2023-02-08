package com.example.news_list.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.favourite.db.FavouriteDAO
import com.example.news_api.Article
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

internal class FavouriteViewModel(private val favouriteDAO: FavouriteDAO) : ViewModel() {

    val favouriteArticles = flow {
        try {
            emit(favouriteDAO.getAll())
        } catch (e: Exception) {
            Log.d(TAG, "Error", e)
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun addArticleToFavourite(article: Article) {
        viewModelScope.launch {
            favouriteDAO.add(article)
        }
    }

    fun deleteByArticle(article: Article) {
        viewModelScope.launch {
            favouriteDAO.deleteByUrl(article.url ?: "")
        }
    }


    class Factory @Inject constructor(
        private val favouriteDAO: Provider<FavouriteDAO>
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == FavouriteViewModel::class.java)
            return FavouriteViewModel(favouriteDAO.get()) as T
        }
    }

    companion object {
        private const val TAG = "FavouriteViewModel"
    }
}