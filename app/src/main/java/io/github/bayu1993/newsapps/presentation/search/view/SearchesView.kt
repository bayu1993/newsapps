package io.github.bayu1993.newsapps.presentation.search.view

import io.github.bayu1993.newsapps.data.response.ArticlesResponse

/**
 * Created by Bayu teguh pamuji on 11/24/18.
 * email : bayuteguhpamuji@gmail.com.
 */
interface SearchesView {
    fun hideView()
    fun showView()
    fun onError(error: Throwable)
    fun showData(data: ArticlesResponse)
}