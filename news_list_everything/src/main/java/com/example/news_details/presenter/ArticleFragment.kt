package com.example.news_details.presenter

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.news_api.Article
import com.example.news_details.R
import com.example.news_details.databinding.FragmentArticlesListBinding
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class ArticleFragment : Fragment(R.layout.fragment_articles_list) {

    @Inject
    internal lateinit var articleViewModelFactory: dagger.Lazy<ArticlesViewModel.Factory>

    private val articlesViewModel: ArticlesViewModel by viewModels { articleViewModelFactory.get() }
    private val newsComponentViewModel: NewsComponentViewModel by viewModels()
    private var articleAdapter: ArticleListAdapter? = null

    private val favouriteListLoadObserver = Observer<List<String>> {
        if(articleAdapter == null){
            this.articleAdapter = ArticleListAdapter(
                clickOnArticle = {},
                addToFavourite = { article -> articlesViewModel.addArticleToFavourite(article); },
                deleteFromFavourite = { article -> articlesViewModel.deleteByArticle(article); },
                it.toHashSet()
            )
        }


        articlesViewModel.getArticles()
    }

    private val articleLoadObserver = Observer<List<Article>> {
        articleAdapter?.let{ list->
            list.submitList(it)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        newsComponentViewModel.articleListComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articlesViewModel.favouriteLiveData.observe(viewLifecycleOwner, favouriteListLoadObserver)
        articlesViewModel.articles.observe(viewLifecycleOwner, articleLoadObserver)
        articlesViewModel.getFavouriteArticles()

        val binding = FragmentArticlesListBinding.bind(view)
        binding.list.adapter = articleAdapter
    }
}