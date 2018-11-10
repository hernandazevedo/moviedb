package com.hernandazevedo.moviedb.data.datasource

import com.hernandazevedo.moviedb.data.api.interfaces.ApiRepository
import com.hernandazevedo.moviedb.data.remote.RemoteMovieSearch
import io.reactivex.Observable

class MovieDataSourceImpl(var apiRepository: ApiRepository) : MovieDataSource {
    override fun findMovieByTitle(title: String): Observable<RemoteMovieSearch> {
        return apiRepository.findMovieByTitle(title)
    }
}