package io.github.bayu1993.newsapps.network

import io.github.bayu1993.newsapps.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Bayu teguh pamuji on 11/24/18.
 * email : bayuteguhpamuji@gmail.com.
 */

object RetrofitService {
    private fun initService(): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return initService().create(service)
    }
}