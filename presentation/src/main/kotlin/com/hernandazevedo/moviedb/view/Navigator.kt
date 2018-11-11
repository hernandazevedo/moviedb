package com.hernandazevedo.moviedb.view

import android.content.Context
import android.support.v4.app.ActivityOptionsCompat
import com.hernandazevedo.moviedb.Movie

interface Navigator {
    fun navigateToDetails(context: Context, options: ActivityOptionsCompat , movie: Movie)
}