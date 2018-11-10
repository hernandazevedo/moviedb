package com.hernandazevedo.moviedb.data.api.interfaces

import com.hernandazevedo.moviedb.data.remote.RemoteMovieSearch
import io.reactivex.Observable

interface ApiRepository {
    fun findMovieByTitle(title: String): Observable<RemoteMovieSearch>
}