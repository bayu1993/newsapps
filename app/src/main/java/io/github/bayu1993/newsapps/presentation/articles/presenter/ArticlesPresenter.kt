package io.github.bayu1993.newsapps.presentation.articles.presenter

import io.github.bayu1993.newsapps.data.response.ArticlesResponse
import io.github.bayu1993.newsapps.network.repo.ArticleRepo
import io.github.bayu1993.newsapps.network.repo.NewsRepoCallback
import io.github.bayu1993.newsapps.presentation.articles.view.ArticlesView

/**
 * Created by Bayu teguh pamuji on 11/24/18.
 * email : bayuteguhpamuji@gmail.com.
 */
class ArticlesPresenter(
    private val view: ArticlesView,
    private val articleRepo: ArticleRepo
) {
    fun getArticles(source: String, apiKey: String) {
        view.hideView()
        articleRepo.getArticles(source, apiKey, object : NewsRepoCallback<ArticlesResponse> {
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