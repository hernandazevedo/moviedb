package com.hernandazevedo.moviedb.data.rest

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiContract {
    companion object {
        const val BASE_OMDB_API_URL = "http://www.omdbapi.com"
    }

    @GET
    @Headers("Content-Type: application/json")
    fun searchMovieByTitle(@Url url: String,
                           @Query("s") title: String,
                           @Query("apikey") serialNumber: String): Single<Response<String>>
}