package com.example.news_dagger_test

import android.app.Application
import com.example.core.depsProviders.FavouriteDBDeps
import com.example.core.depsProviders.FavouriteDBDepsProvider
import com.example.news_dagger_test.di.AppComponent
import com.example.news_dagger_test.di.DaggerAppComponent
import com.example.news_details.di.NewsApiDeps
import com.example.news_details.di.NewsApiDepsProvider

class App : Application(), NewsApiDepsProvider, FavouriteDBDepsProvider {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .apiKey(BuildConfig.API_KEY)
            .build()
    }

    override val newsApiDeps: NewsApiDeps = appComponent
    override val favouriteDBDeps: FavouriteDBDeps = appComponent
}