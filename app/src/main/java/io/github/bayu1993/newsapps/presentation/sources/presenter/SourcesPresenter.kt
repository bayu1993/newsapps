package io.github.bayu1993.newsapps.presentation.sources.presenter

import io.github.bayu1993.newsapps.data.response.SourcesResponse
import io.github.bayu1993.newsapps.network.repo.NewsRepoCallback
import io.github.bayu1993.newsapps.network.repo.SourceRepo
import io.github.bayu1993.newsapps.presentation.sources.view.SourcesView

/**
 * Created by Bayu teguh pamuji on 11/23/18.
 * email : bayuteguhpamuji@gmail.com.
 */

class SourcesPresenter(
    private val view: SourcesView,
    private val sourceRepo: SourceRepo
) {
    fun getSources(apiKey: String) {
        view.hideView()
        sourceRepo.getSources(apiKey, object : NewsRepoCallback<SourcesResponse> {
            override fun onDataLoad(data: SourcesResponse) {
                view.showData(data)
            }

            override fun onError(error: Throwable) {
                view.onError(error)
            }

        })
        view.showView()
    }

}