package io.github.bayu1993.newsapps.network.repo

/**
 * Created by Bayu teguh pamuji on 11/24/18.
 * email : bayuteguhpamuji@gmail.com.
 */

interface NewsRepoCallback<T> {
    fun onDataLoad(data: T)
    fun onError(error: Throwable)
}