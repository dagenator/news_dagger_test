package com.example.news_details.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.favourite.db.FavouriteDAO
import com.example.news_api.Article
import com.example.news_api.NewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Provider

internal class ArticlesViewModel(
    private val newsService: NewsService,
    private val favouriteDAO: FavouriteDAO
) : ViewModel() {

    val favouriteLiveData: MutableLiveData<List<String>> = MutableLiveData(emptyList())


    val articles = MutableLiveData<List<Article>>(emptyList())

    fun getArticles() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                articles.postValue(newsService.everything().articles)
            }
        }
    }
//

    fun addArticleToFavourite(article: Article) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                favouriteDAO.add(article)
            }
        }
    }

    fun deleteByArticle(article: Article) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                favouriteDAO.deleteByUrl(article.url ?: "")
            }
        }
    }

    fun getFavouriteArticles() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                favouriteLiveData.postValue(favouriteDAO.getAllUrls())
            }
        }
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