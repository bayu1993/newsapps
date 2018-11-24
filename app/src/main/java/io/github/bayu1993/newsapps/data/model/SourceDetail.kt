package io.github.bayu1993.newsapps.data.model

import com.google.gson.annotations.SerializedName

data class SourceDetail(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String
)
