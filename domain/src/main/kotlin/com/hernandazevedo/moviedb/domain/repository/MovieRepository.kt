package com.hernandazevedo.moviedb.domain.repository

import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.MovieDetail
import io.reactivex.Observable

interface MovieRepository {
    fun findMovieByTitle(title: String): Observable<List<Movie>>
    fun findMovieByImdbID(imdbID: String): Observable<MovieDetail>
}