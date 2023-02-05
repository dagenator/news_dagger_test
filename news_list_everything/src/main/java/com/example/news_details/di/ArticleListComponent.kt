package com.example.news_details.di

import android.app.Application
import android.content.Context
import com.example.core.depsProviders.FavouriteDBDeps
import com.example.core.depsProviders.FavouriteDBDepsProvider
import com.example.news_api.NewsService
import com.example.news_details.presenter.ArticleFragment
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Scope
internal annotation class NewsDetailsScope

@[NewsDetailsScope Component(
    dependencies = [NewsApiDeps::class],
    modules = [NewsDetailsModule::class]
)]
interface ArticleListComponent {

    fun inject(articleFragment: ArticleFragment)

    @Component.Builder
    interface Builder {
        fun newsDeps(newsDetailsDeps: NewsApiDeps): Builder
        fun favouriteDeps(favouriteDBDeps: FavouriteDBDeps): Builder
        fun build(): ArticleListComponent
    }
}

@Module
internal class NewsDetailsModule {

}

interface NewsApiDepsProvider {
    val newsApiDeps: NewsApiDeps
}

interface NewsApiDeps {
    val NewsService: NewsService
}

val Context.newsDetailsDepsProvider: NewsApiDepsProvider
    get() = when (this){
        is NewsApiDepsProvider -> this
        is Application -> error("Application must implements News DetailsDepsProvider")
        else -> applicationContext.newsDetailsDepsProvider
    }


val Context.favouriteDBDepsProvider: FavouriteDBDepsProvider
    get() = when (this){
        is FavouriteDBDepsProvider -> this
        is Application -> error("Application must implements News DetailsDepsProvider")
        else -> applicationContext.favouriteDBDepsProvider
    }

