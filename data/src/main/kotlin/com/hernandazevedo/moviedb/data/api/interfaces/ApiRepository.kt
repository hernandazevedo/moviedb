package com.hernandazevedo.moviedb.data.api.interfaces

import com.hernandazevedo.moviedb.data.api.remote.RemoteMovieDetail
import com.hernandazevedo.moviedb.data.api.remote.RemoteMovieSearch
import io.reactivex.Observable

interface ApiRepository {
    fun findMovieByTitle(title: String): Observable<RemoteMovieSearch>
    fun findMovieByImdbId(imdbId: String): Observable<RemoteMovieDetail>
}