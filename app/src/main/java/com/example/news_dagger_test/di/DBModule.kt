package com.example.news_dagger_test.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.core.favourite.db.FavouriteDAO
import com.example.core.favourite.db.FavouriteDB
import dagger.Module
import dagger.Provides

@Module
class DBModule {

    @[AppScope Provides]
    fun provideFavouriteDB(context: Application): FavouriteDB {
        val db = Room.databaseBuilder(
            context,
            FavouriteDB::class.java, "database-name"
        ).build()
        Log.i("TestTag", "provideFavouriteDB: " + db.isOpen+" "+ (db.favouriteDao() != null))
        return db
    }

    @[AppScope Provides]
    fun provideFavouriteDAO(db : FavouriteDB): FavouriteDAO {
        return db.favouriteDao()
    }

}