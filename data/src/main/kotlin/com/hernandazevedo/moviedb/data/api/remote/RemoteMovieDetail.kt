package com.hernandazevedo.moviedb.data.api.remote

import com.google.gson.annotations.SerializedName

data class RemoteMovieDetail(@SerializedName("imdbID") val imdbID: String,
                             @SerializedName("Title") val title: String,
                             @SerializedName("Year") val year: String,
                             @SerializedName("Type") val type: String,
                             @SerializedName("Poster") val posterUrl: String,
                             @SerializedName("Plot") val plot: String,
                             @SerializedName("imdbRating") val imdbRating: String,
                             @SerializedName("Genre") val genre: String
                             )
