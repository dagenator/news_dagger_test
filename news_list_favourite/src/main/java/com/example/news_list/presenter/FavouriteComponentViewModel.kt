package com.example.news_list.presenter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.news_list.di.DaggerFavouriteComponent
import com.example.news_list.di.FavouriteComponent
import com.example.news_list.di.favouriteDBDepsProvider

internal class FavouriteComponentViewModel (application: Application) : AndroidViewModel(application) {
    val favouriteComponent: FavouriteComponent by lazy {
        DaggerFavouriteComponent.builder()
            .deps(application.favouriteDBDepsProvider.favouriteDBDeps)
            .build()
    }
}