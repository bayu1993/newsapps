package io.github.bayu1993.newsapps.presentation.sources.view

import io.github.bayu1993.newsapps.data.response.SourcesResponse

/**
 * Created by Bayu teguh pamuji on 11/23/18.
 * email : bayuteguhpamuji@gmail.com.
 */
interface SourcesView {
    fun hideView()
    fun showView()
    fun onError(error: Throwable)
    fun showData(data: SourcesResponse)
}