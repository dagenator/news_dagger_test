package com.example.news_details.presenter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.databinding.ItemArticleBinding
import com.example.news_api.Article

class ArticleListAdapter(
    private val clickOnArticle: (Article) -> Unit,
    private val addToFavourite: (Article) -> Unit,
    private val deleteFromFavourite: (Article) -> Unit,
    private val favouriteUrlsList: HashSet<String>?
) : androidx.recyclerview.widget.ListAdapter<Article, ArticleListAdapter.ViewHolder>(
    ArticleItemCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int ) {
        holder.bind(getItem(position), clickOnArticle, addToFavourite, deleteFromFavourite, favouriteUrlsList)
    }

    inner class ViewHolder(val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun paintLikeButton(isFavourite:Boolean){
            binding.likeButton.drawable.setTint(if(isFavourite) Color.GRAY else Color.MAGENTA)
        }

        fun bind(
            article: Article,
            clickOnArticle: (Article) -> Unit,
            addToFavourite: (Article) -> Unit,
            deleteFromFavourite: (Article) -> Unit,
            favouriteUrlsList: HashSet<String>?
        ) {
            val isFavourite = favouriteUrlsList?.contains(article.url) ?: true
            binding.title.text = article.title
            binding.content.text = article.content
            binding.likeButton.setOnClickListener(OnClickListener {
                paintLikeButton(isFavourite)
                if(isFavourite) deleteFromFavourite(article) else addToFavourite(article)
            })
        }
    }

}

private class ArticleItemCallback() : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }
}