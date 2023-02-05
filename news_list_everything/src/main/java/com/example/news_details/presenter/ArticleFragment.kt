package com.example.news_details.presenter

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_api.Article
import com.example.news_details.R
import com.example.news_details.databinding.FragmentArticlesListBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class ArticleFragment : Fragment(R.layout.fragment_articles_list) {

    @Inject
    internal lateinit var articleViewModelFactory: dagger.Lazy<ArticlesViewModel.Factory>

    private val articlesViewModel: ArticlesViewModel by viewModels { articleViewModelFactory.get() }
    private val newsComponentViewModel: NewsComponentViewModel by viewModels()
    private var articleAdapter: ArticleAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        newsComponentViewModel.articleListComponent.inject(this)
    }

    fun doOnClick(a:Article){
        articlesViewModel.addToFavourite(a);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.articleAdapter = ArticleAdapter( val x-> doOnClick(x))

        val binding = FragmentArticlesListBinding.bind(view)
        binding.list.adapter = articleAdapter

        articlesViewModel.articles.onEach { articles ->
            articleAdapter?.submitList(articles)
        }.launchIn(lifecycleScope)
    }
}