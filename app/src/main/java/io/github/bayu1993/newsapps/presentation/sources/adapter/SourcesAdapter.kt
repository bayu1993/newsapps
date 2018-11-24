package io.github.bayu1993.newsapps.presentation.sources.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.bayu1993.newsapps.R
import io.github.bayu1993.newsapps.data.model.SourceDetail
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_sources.*

/**
 * Created by Bayu teguh pamuji on 11/23/18.
 * email : bayuteguhpamuji@gmail.com.
 */

class SourcesAdapter(private val sourcesList: MutableList<SourceDetail>, private val listener: (SourceDetail) -> Unit) :
    RecyclerView.Adapter<SourcesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sources, parent, false))
    }

    override fun getItemCount() = sourcesList.size

    override fun onBindViewHolder(holder: SourcesAdapter.ViewHolder, position: Int) {
        holder.bind(sourcesList[position], listener)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: SourceDetail, listener: (SourceDetail) -> Unit) {
            tv_sources.text = item.name

            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}