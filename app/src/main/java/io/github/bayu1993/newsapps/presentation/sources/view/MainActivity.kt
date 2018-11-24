package io.github.bayu1993.newsapps.presentation.sources.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.google.gson.Gson
import io.github.bayu1993.newsapps.BuildConfig
import io.github.bayu1993.newsapps.R
import io.github.bayu1993.newsapps.data.model.SourceDetail
import io.github.bayu1993.newsapps.data.response.SourcesResponse
import io.github.bayu1993.newsapps.network.repo.SourceRepo
import io.github.bayu1993.newsapps.presentation.articles.view.ArticlesActivity
import io.github.bayu1993.newsapps.presentation.search.view.SearchActivity
import io.github.bayu1993.newsapps.presentation.sources.adapter.SourcesAdapter
import io.github.bayu1993.newsapps.presentation.sources.presenter.SourcesPresenter
import io.github.bayu1993.newsapps.utils.gone
import io.github.bayu1993.newsapps.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), SourcesView {
    companion object {
        const val KEY_SOURCES = "KEY_SOURCES"
        const val KEY_SEARCH = "KEY_SEARCH"
    }

    private var sourcesList: MutableList<SourceDetail> = mutableListOf()
    private var searchView: SearchView? = null
    private lateinit var queryTextListener: SearchView.OnQueryTextListener
    private lateinit var presenter: SourcesPresenter
    private lateinit var adapter: SourcesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        getNews()

    }

    private fun setUpView() {
        adapter = SourcesAdapter(sourcesList) {
            startActivity<ArticlesActivity>(KEY_SOURCES to it.id)
        }
        rv_sources.layoutManager = GridLayoutManager(this, 2)
        rv_sources.adapter = adapter
    }

    private fun getNews() {
        presenter = SourcesPresenter(this, SourceRepo())
        presenter.getSources(BuildConfig.API_KEY)
    }

    override fun hideView() {
        rv_sources.gone()
        progressBar.visible()
    }

    override fun showView() {
        rv_sources.visible()
        progressBar.gone()
    }

    override fun onError(error: Throwable) {
        Log.e("error", error.message)
        hideView()
        toast(error.message.toString())
    }

    override fun showData(data: SourcesResponse) {
        Log.d("test", Gson().toJsonTree(data).toString())
        sourcesList.clear()
        sourcesList.addAll(data.sources)
        adapter.notifyDataSetChanged()
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
                override fun onQueryTextSubmit(query: String?): Boolean {
                    startActivity<SearchActivity>(KEY_SEARCH to query)
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return true
                }

            }
            searchView?.setOnQueryTextListener(queryTextListener)
        }
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
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
