package io.github.bayu1993.newsapps.presentation.articles.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.google.gson.Gson
import io.github.bayu1993.newsapps.BuildConfig
import io.github.bayu1993.newsapps.R
import io.github.bayu1993.newsapps.data.model.ArticleDetail
import io.github.bayu1993.newsapps.data.response.ArticlesResponse
import io.github.bayu1993.newsapps.network.repo.ArticleRepo
import io.github.bayu1993.newsapps.presentation.articles.adapter.ArticlesAdapter
import io.github.bayu1993.newsapps.presentation.articles.presenter.ArticlesPresenter
import io.github.bayu1993.newsapps.presentation.detail.DetailActivity
import io.github.bayu1993.newsapps.presentation.sources.view.MainActivity.Companion.KEY_SOURCES
import io.github.bayu1993.newsapps.utils.gone
import io.github.bayu1993.newsapps.utils.visible
import kotlinx.android.synthetic.main.activity_articles.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ArticlesActivity : AppCompatActivity(), ArticlesView {
    companion object {
        const val KEY_ARTICLES = "KEY_ARTICLES"
    }

    private var articleList: MutableList<ArticleDetail> = mutableListOf()
    private var articleFilter = mutableListOf<ArticleDetail>()
    private lateinit var articleResponse: ArticlesResponse
    private lateinit var presenter: ArticlesPresenter
    private lateinit var adapter: ArticlesAdapter
    private var searchView: SearchView? = null
    private lateinit var queryTextListener: SearchView.OnQueryTextListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)
        setUpView()
        getArticles()
    }

    private fun getArticles() {
        val idSource = intent.getStringExtra(KEY_SOURCES)
        var title = idSource
        title = title.replace("-", " ")
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = ArticlesPresenter(this, ArticleRepo())
        presenter.getArticles(idSource, BuildConfig.API_KEY)
    }

    private fun setUpView() {
        adapter = ArticlesAdapter(articleList) {
            startActivity<DetailActivity>(KEY_ARTICLES to it.url)
        }
        rv_article.layoutManager = LinearLayoutManager(this)
        rv_article.adapter = adapter
    }

    override fun hideView() {
        rv_article.gone()
        progress_bar.visible()
    }

    override fun showView() {
        rv_article.visible()
        progress_bar.gone()
    }

    override fun onError(error: Throwable) {
        Log.e("test article error", error.message)
        toast(error.message.toString())
    }

    override fun showData(data: ArticlesResponse) {
        Log.d("test article", Gson().toJsonTree(data.articles).toString())
        articleList.clear()
        articleResponse = data
        articleList.addAll(articleResponse.articles)
        adapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.search_article, menu)
        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = searchItem.actionView as SearchView

        if (null != searchView) {
            searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {

                    return false
                }

                override fun onQueryTextChange(query: String): Boolean {
                    getSearch(query)
                    return true
                }

            }
            searchView?.setOnQueryTextListener(queryTextListener)
        }
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
                articleFilter.clear()
                showData(articleResponse)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun getSearch(query: String) {
        if (query.isNotBlank()) {
            articleFilter.clear()
            articleList.filterTo(articleFilter) { it.title?.toLowerCase()!!.contains(query.toLowerCase()) }
            articleList.clear()
            articleList.addAll(articleFilter)
            adapter.notifyDataSetChanged()
        } else {
            showData(articleResponse)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                return false
            }
        }
        searchView?.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }
}
