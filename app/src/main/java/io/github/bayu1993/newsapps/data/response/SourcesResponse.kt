package io.github.bayu1993.newsapps.data.response

import com.google.gson.annotations.SerializedName
import io.github.bayu1993.newsapps.data.model.SourceDetail

/**
 * Created by Bayu teguh pamuji on 11/23/18.
 * email : bayuteguhpamuji@gmail.com.
 */

data class SourcesResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("sources")
    val sources: List<SourceDetail>
)