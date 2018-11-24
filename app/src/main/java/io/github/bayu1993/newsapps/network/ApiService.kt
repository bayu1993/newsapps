package io.github.bayu1993.newsapps.network

import io.github.bayu1993.newsapps.data.response.ArticlesResponse
import io.github.bayu1993.newsapps.data.response.SourcesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Bayu teguh pamuji on 11/23/18.
 * email : bayuteguhpamuji@gmail.com.
 */
interface ApiService {
    @GET("v2/sources")
    fun getSources(@Query("apiKey") apiKey: String): Observable<SourcesResponse>

    @GET("v2/top-headlines")
    fun getArticles(@Query("sources") sources: String, @Query("apiKey") apiKey: String): Observable<ArticlesResponse>

    @GET("v2/everything")
    fun getSearchArticles(@Query("q") search: String, @Query("apiKey") apiKey: String): Observable<ArticlesResponse>
}