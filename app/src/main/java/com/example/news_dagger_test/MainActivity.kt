package com.example.news_dagger_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.news_details.presenter.ArticleFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      Log.i("MainActivityTAG", "onCreate: ")
      val fragmentManager = supportFragmentManager
      if (fragmentManager.findFragmentById(R.id.main_fragment_container) == null) {
         fragmentManager.commit {
            add<ArticleFragment>(R.id.main_fragment_container, FRAGMENT_ARTICLES)
         }
      }
   }

   private companion object {
      private const val FRAGMENT_ARTICLES = "articles"
   }


}