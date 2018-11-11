package com.hernandazevedo.moviedb.data.datasource.local

import com.hernandazevedo.moviedb.Movie
import io.reactivex.Single

interface MovieLocalDataSource {
    fun insertMovie(movie: Movie): Single<Long>
}