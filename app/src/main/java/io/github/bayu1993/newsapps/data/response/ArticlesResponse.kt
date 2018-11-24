package io.github.bayu1993.newsapps.data.response

import com.google.gson.annotations.SerializedName
import io.github.bayu1993.newsapps.data.model.ArticleDetail

/**
 * Created by Bayu teguh pamuji on 11/23/18.
 * email : bayuteguhpamuji@gmail.com.
 */
data class ArticlesResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("articles")
    val articles: List<ArticleDetail>
)