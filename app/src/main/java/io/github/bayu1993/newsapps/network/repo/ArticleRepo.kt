package io.github.bayu1993.newsapps.network.repo

import io.github.bayu1993.newsapps.data.response.ArticlesResponse
import io.github.bayu1993.newsapps.network.ApiService
import io.github.bayu1993.newsapps.network.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Bayu teguh pamuji on 11/24/18.
 * email : bayuteguhpamuji@gmail.com.
 */
class ArticleRepo {
    fun getArticles(source: String, apiKey: String, callback: NewsRepoCallback<ArticlesResponse>) {
        RetrofitService.createService(ApiService::class.java)
            .getArticles(source, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                callback.onDataLoad(data)
            }, { error ->
                callback.onError(error)
            })

    }

    fun getSearchArticles(search: String, apiKey: String, callback: NewsRepoCallback<ArticlesResponse>) {
        RetrofitService.createService(ApiService::class.java)
            .getSearchArticles(search, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                callback.onDataLoad(data)
            }, { error ->
                callback.onError(error)
            })
    }
}