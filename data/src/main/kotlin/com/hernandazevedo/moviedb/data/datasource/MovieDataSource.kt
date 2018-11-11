package com.hernandazevedo.moviedb.data.datasource

import com.hernandazevedo.moviedb.data.api.remote.RemoteMovieDetail
import com.hernandazevedo.moviedb.data.api.remote.RemoteMovieSearch
import io.reactivex.Observable

interface MovieDataSource {

    fun findMovieByTitle(title: String): Observable<RemoteMovieSearch>

    fun findMovieByImdbId(imdbID: String): Observable<RemoteMovieDetail>
}
