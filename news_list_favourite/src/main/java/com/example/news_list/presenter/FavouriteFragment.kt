package com.example.news_list.presenter

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.news_list.R
import com.example.news_list.databinding.FragmentArticlesListBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FavouriteFragment : Fragment(R.layout.fragment_articles_list) {

    @Inject
    internal lateinit var favouriteViewModelFactory: dagger.Lazy<FavouriteViewModel.Factory>

    private val favouriteViewModel: FavouriteViewModel by viewModels { favouriteViewModelFactory.get() }
    private val favouriteComponentViewModel: FavouriteComponentViewModel by viewModels()
    private var favouriteAdapter: FavouriteAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        favouriteComponentViewModel.favouriteComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.favouriteAdapter = FavouriteAdapter(View.OnClickListener {

        })

        val binding = FragmentArticlesListBinding.bind(view)
        binding.list.adapter = favouriteAdapter

        favouriteViewModel.articles.onEach { articles ->
            favouriteAdapter?.submitList(articles)
        }.launchIn(lifecycleScope)
    }
}