package com.hernandazevedo.moviedb.domain.repository

import com.hernandazevedo.moviedb.Movie
import io.reactivex.Observable

interface MovieRepository {
    fun findMovieByTitle(title: String): Observable<List<Movie>>
}