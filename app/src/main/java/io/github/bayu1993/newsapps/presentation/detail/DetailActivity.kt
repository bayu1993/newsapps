package io.github.bayu1993.newsapps.presentation.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import io.github.bayu1993.newsapps.presentation.articles.view.ArticlesActivity.Companion.KEY_ARTICLES

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webUrl = intent.getStringExtra(KEY_ARTICLES)
        val webView = WebView(this)
        webView.loadUrl(webUrl)
        setContentView(webView)
    }
}
