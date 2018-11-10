package com.hernandazevedo.moviedb.data.datasource

import com.hernandazevedo.moviedb.data.remote.RemoteMovieSearch
import io.reactivex.Observable

interface MovieDataSource {

    fun findMovieByTitle(title: String): Observable<RemoteMovieSearch>
}
