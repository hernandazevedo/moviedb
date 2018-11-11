package com.hernandazevedo.moviedb.view

import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.view.details.DetailsActivity
import com.hernandazevedo.moviedb.view.details.DetailsActivity.Companion.INTENT_MOVIE_SELECTED

class NavigatorImpl : Navigator {
    override fun navigateToDetails(context: Context, options: ActivityOptionsCompat, movie: Movie) {
        context.startActivity(Intent(context, DetailsActivity::class.java).apply {
            putExtra(INTENT_MOVIE_SELECTED, movie)
        }, options.toBundle())
    }
}