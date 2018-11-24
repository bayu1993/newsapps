package io.github.bayu1993.newsapps.presentation.search.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import io.github.bayu1993.newsapps.BuildConfig
import io.github.bayu1993.newsapps.R
import io.github.bayu1993.newsapps.data.model.ArticleDetail
import io.github.bayu1993.newsapps.data.response.ArticlesResponse
import io.github.bayu1993.newsapps.network.repo.ArticleRepo
import io.github.bayu1993.newsapps.presentation.articles.adapter.ArticlesAdapter
import io.github.bayu1993.newsapps.presentation.articles.view.ArticlesActivity.Companion.KEY_ARTICLES
import io.github.bayu1993.newsapps.presentation.detail.DetailActivity
import io.github.bayu1993.newsapps.presentation.search.presenter.SearchPresenter
import io.github.bayu1993.newsapps.presentation.sources.view.MainActivity.Companion.KEY_SEARCH
import io.github.bayu1993.newsapps.utils.gone
import io.github.bayu1993.newsapps.utils.visible
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SearchActivity : AppCompatActivity(), SearchesView {
    private var articleList: MutableList<ArticleDetail> = mutableListOf()
    private lateinit var presenter: SearchPresenter
    private lateinit var adapter: ArticlesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setUpView()
        getSearch()
    }

    private fun setUpView() {
        adapter = ArticlesAdapter(articleList) {
            startActivity<DetailActivity>(KEY_ARTICLES to it.url)
        }
        rv_search.layoutManager = LinearLayoutManager(this)
        rv_search.adapter = adapter
    }

    private fun getSearch() {
        val searchResult = intent.getStringExtra(KEY_SEARCH)
        supportActionBar?.title = searchResult
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = SearchPresenter(this, ArticleRepo())
        presenter.getSearchArticles(searchResult, BuildConfig.API_KEY)
    }

    override fun hideView() {
        rv_search.gone()
        progress_bar_search.visible()
    }

    override fun showView() {
        rv_search.visible()
        progress_bar_search.gone()
    }

    override fun onError(error: Throwable) {
        toast(error.message.toString())
    }

    override fun showData(data: ArticlesResponse) {
        articleList.clear()
        articleList.addAll(data.articles)
        adapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
