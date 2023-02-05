package com.example.news_dagger_test.di

import android.app.Application
import androidx.room.Room
import com.example.core.db.FavouriteDAO
import com.example.core.db.FavouriteDB
import dagger.Module
import dagger.Provides

@Module
class DBModule {

    @[AppScope Provides]
    fun provideFavouriteDB(context: Application): FavouriteDB {
        return Room.databaseBuilder(
            context,
            FavouriteDB::class.java, "FavouriteDatabase"
        ).build()
    }

    @[AppScope Provides]
    fun provideFavouriteDAO(db : FavouriteDB): FavouriteDAO {
        return db.favouriteDao()
    }

}