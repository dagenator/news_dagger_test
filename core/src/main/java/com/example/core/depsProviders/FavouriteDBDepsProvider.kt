package com.example.core.depsProviders

import com.example.core.db.FavouriteDAO

interface FavouriteDBDepsProvider {
    val favouriteDBDeps: FavouriteDBDeps
}

interface FavouriteDBDeps {
    val favouriteDAO: FavouriteDAO
}
