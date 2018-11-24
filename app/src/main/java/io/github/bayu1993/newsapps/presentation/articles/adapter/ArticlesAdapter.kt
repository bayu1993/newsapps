package io.github.bayu1993.newsapps.presentation.articles.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import io.github.bayu1993.newsapps.R
import io.github.bayu1993.newsapps.data.model.ArticleDetail
import io.github.bayu1993.newsapps.presentation.articles.view.ArticlesActivity
import io.github.bayu1993.newsapps.utils.dateFormat
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.*

/**
 * Created by Bayu teguh pamuji on 11/24/18.
 * email : bayuteguhpamuji@gmail.com.
 */

class ArticlesAdapter(
    private val articleList: MutableList<ArticleDetail>,
    private val listener: (ArticleDetail) -> Unit
) :
    RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))
    }

    override fun getItemCount() = articleList.size

    override fun onBindViewHolder(holder: ArticlesAdapter.ViewHolder, position: Int) {
        holder.bind(articleList[position], listener)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: ArticleDetail, listener: (ArticleDetail) -> Unit) {
            tv_title.text = item.title
            tv_author.text = item.author
            tv_source.text = item.source.name
            tv_date.text = dateFormat(item.date)
            Picasso.get().load(item.imgUrl).into(img_news)

            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}