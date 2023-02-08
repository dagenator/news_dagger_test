package com.example.core.favourite.depsProviders

import com.example.core.favourite.db.FavouriteDAO

interface FavouriteDBDepsProvider {
    val favouriteDBDeps: FavouriteDBDeps
}

interface FavouriteDBDeps {
    val favouriteDAO: FavouriteDAO
}
