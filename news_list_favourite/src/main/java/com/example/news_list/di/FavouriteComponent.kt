package com.example.news_list.di

import android.app.Application
import android.content.Context
import com.example.core.favourite.depsProviders.FavouriteDBDeps
import com.example.core.favourite.depsProviders.FavouriteDBDepsProvider
import com.example.news_api.NewsService
import com.example.news_list.presenter.FavouriteFragment
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Scope
internal annotation class FavouriteScope

@[FavouriteScope Component(
    dependencies = [FavouriteDBDeps::class],
    modules = [FavouriteModule::class]
)]
interface FavouriteComponent {
    fun inject(favouriteFragment: FavouriteFragment)

    @Component.Builder
    interface Builder {
        fun deps(favouriteDBDeps: FavouriteDBDeps): Builder
        fun build(): FavouriteComponent
    }
}

@Module
internal class FavouriteModule {

}

val Context.favouriteDBDepsProvider: FavouriteDBDepsProvider
    get() = when (this){
        is FavouriteDBDepsProvider -> this
        is Application -> error("Application must implements News DetailsDepsProvider")
        else -> applicationContext.favouriteDBDepsProvider
    }

