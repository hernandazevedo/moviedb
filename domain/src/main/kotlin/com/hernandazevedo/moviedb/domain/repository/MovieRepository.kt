package com.hernandazevedo.moviedb.domain.repository

import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.MovieDetail
import io.reactivex.Observable

interface MovieRepository {
    fun findSavedMoviesOnLocalDatabase(): Observable<List<Movie>>
    fun findMovieByTitle(title: String): Observable<List<Movie>>
    fun findMovieByImdbID(imdbID: String): Observable<MovieDetail>
    fun saveMovieToLocalDatabase(movie: Movie): Observable<Long>
    fun deleteMovieFromLocalDatabase(imdbID: String): Observable<Long>
}