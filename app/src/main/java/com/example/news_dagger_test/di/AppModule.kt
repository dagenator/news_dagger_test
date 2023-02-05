package com.example.news_dagger_test.di

import com.example.news_api.NewsService
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @[Provides AppScope]
    fun provideNewsService(@NewsApiQualifier apiKey: String) = NewsService(apiKey)
}