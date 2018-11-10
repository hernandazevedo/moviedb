package com.hernandazevedo.moviedb.data.remote

import com.google.gson.annotations.SerializedName

data class RemoteMovie(@SerializedName("imdbID") val imdbID: String,
                       @SerializedName("Title") val title: String,
                       @SerializedName("Year") val year: String,
                       @SerializedName("Type") val type: String,
                       @SerializedName("Poster") val posterUrl: String)
