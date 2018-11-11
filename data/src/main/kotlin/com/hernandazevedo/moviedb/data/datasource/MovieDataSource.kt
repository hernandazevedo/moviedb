package com.hernandazevedo.moviedb.data.datasource

import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.data.api.remote.RemoteMovieDetail
import com.hernandazevedo.moviedb.data.api.remote.RemoteMovieSearch
import com.hernandazevedo.moviedb.data.entity.MovieEntity
import io.reactivex.Observable

interface MovieDataSource {

    fun findMovieByTitle(title: String): Observable<RemoteMovieSearch>

    fun findMovieByImdbId(imdbID: String): Observable<RemoteMovieDetail>
    fun saveMovieToLocalDatabase(movie: Movie): Observable<Long>
    fun deleteMovieFromLocalDatabase(imdbID: String): Observable<Long>
    fun findMovieOnLocalDatabaseByImdbId(imdbID: String): MovieEntity
    fun findSavedMoviesOnLocalDatabase(): Observable<List<MovieEntity>>
}
