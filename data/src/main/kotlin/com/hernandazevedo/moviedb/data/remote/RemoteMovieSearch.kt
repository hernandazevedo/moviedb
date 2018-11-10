package com.hernandazevedo.moviedb.data.remote

import com.google.gson.annotations.SerializedName

data class RemoteMovieSearch(@SerializedName("Search") val search: List<RemoteMovie>,
                             @SerializedName("totalResults") val totalResults: String,
                             @SerializedName("Response") val response: String)