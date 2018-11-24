package io.github.bayu1993.newsapps.presentation.search.presenter

import io.github.bayu1993.newsapps.data.response.ArticlesResponse
import io.github.bayu1993.newsapps.network.repo.ArticleRepo
import io.github.bayu1993.newsapps.network.repo.NewsRepoCallback
import io.github.bayu1993.newsapps.presentation.search.view.SearchesView

/**
 * Created by Bayu teguh pamuji on 11/24/18.
 * email : bayuteguhpamuji@gmail.com.
 */
class SearchPresenter(private val view: SearchesView, private val articleRepo: ArticleRepo) {
    fun getSearchArticles(search: String, apiKey: String) {
        view.hideView()
        articleRepo.getSearchArticles(search, apiKey, object : NewsRepoCallback<ArticlesResponse> {
            override fun onDataLoad(data: ArticlesResponse) {
                view.showData(data)
            }

            override fun onError(error: Throwable) {
                view.onError(error)
            }

        })
        view.showView()
    }
}