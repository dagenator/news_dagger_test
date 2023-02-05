package com.example.news_details.presenter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.news_details.di.DaggerNewsDetailsComponent
import com.example.news_details.di.ArticleListComponent
import com.example.news_details.di.favouriteDBDepsProvider
import com.example.news_details.di.newsDetailsDepsProvider

internal class NewsComponentViewModel (application: Application) :AndroidViewModel(application) {
    val articleListComponent: ArticleListComponent by lazy {
        DaggerNewsDetailsComponent.builder()
            .newsDeps(application.newsDetailsDepsProvider.newsApiDeps)
            .favouriteDeps(application.favouriteDBDepsProvider.favouriteDBDeps)
            .build()

    }
}