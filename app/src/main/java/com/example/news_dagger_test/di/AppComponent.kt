package com.example.news_dagger_test.di

import android.app.Application
import com.example.core.db.FavouriteDAO
import com.example.core.depsProviders.FavouriteDBDeps
import com.example.news_api.NewsService
import com.example.news_details.di.NewsApiDeps
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope
@[AppScope Component(modules = [AppModule::class, DBModule::class])]
interface AppComponent:NewsApiDeps, FavouriteDBDeps {

    override val NewsService: NewsService

    override val favouriteDAO: FavouriteDAO

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiKey(@NewsApiQualifier apiKey: String): Builder

        fun build(): AppComponent
    }
}



@Qualifier
annotation class NewsApiQualifier

@Scope
annotation class AppScope
