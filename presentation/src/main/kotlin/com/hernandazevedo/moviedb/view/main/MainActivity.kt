package com.hernandazevedo.moviedb.view.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.R
import com.hernandazevedo.moviedb.data.Logger
import com.hernandazevedo.moviedb.getFactoryViewModel
import com.hernandazevedo.moviedb.view.Navigator
import com.hernandazevedo.moviedb.view.adapter.MovieAdapter
import com.hernandazevedo.moviedb.view.base.BaseActivity
import com.hernandazevedo.moviedb.view.base.Resource
import com.hernandazevedo.moviedb.view.base.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

class MainActivity : BaseActivity() , NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject lateinit var navigator: Navigator

    val layoutManager = LinearLayoutManager(this)

    val movieAdapter by lazy {
        MovieAdapter(context = this@MainActivity,
            itemClick = { movie, options ->
                navigator.navigateToDetails(this@MainActivity, options, movie)
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = getFactoryViewModel { mainViewModel }
        setContentView(R.layout.activity_main)
        setupRecyclersView()
        setupFilterNavigationDrawer()
        setupToolbar()
        setupButtons()
        subscribeToSearchMovie()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_filter, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_filter -> {
            drawerLayout.openDrawer(GravityCompat.END)
            true
        }
        R.id.action_filter_my_favorites -> {
            showMessage(getText(R.string.searching_favorites).toString())
            mainViewModel.fetchMyfavorites()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.END)
        return true
    }

    private fun subscribeToSearchMovie() {
        mainViewModel.responseSearchMovie.observe(this,
            Observer<Resource<List<Movie>>> {
                when (it?.status) {
                    Status.SUCCESS -> {
                        Logger.d("Success finding movie")
                        showMovies(it.data)
                    }
                    Status.ERROR -> {
                        Logger.d("Error finding movie")
                        showMessage("Error finding movie")
                        it.throwable?.printStackTrace()
                    }
                }
            })
    }

    private fun showMovies(movieList: List<Movie>?) {
        movieAdapter.setMovies(movieList)
    }

    private fun setupRecyclersView() {
        moviesRecyclerView.layoutManager = layoutManager
        moviesRecyclerView.adapter = movieAdapter
        moviesRecyclerView.setEmptyView(emptyView)
    }

    private fun setupFilterNavigationDrawer() {
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.setDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white))
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = ""
    }

    private fun setupButtons() {
        searchMovieButton.setOnClickListener {
            mainViewModel.searchMovie(movieTitleEditText.text.toString())
            drawerLayout.closeDrawer(GravityCompat.END)
            hideKeyboard()
        }
    }
}
