package io.github.bayu1993.newsapps.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Bayu teguh pamuji on 11/23/18.
 * email : bayuteguhpamuji@gmail.com.
 */
data class ArticleDetail(
    @SerializedName("source")
    val source: SourceDetail,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val imgUrl: String?,
    @SerializedName("publishedAt")
    val date: Date?
)