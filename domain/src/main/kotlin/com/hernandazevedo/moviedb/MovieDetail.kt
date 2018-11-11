package com.hernandazevedo.moviedb

import java.io.Serializable

data class MovieDetail(val imdbID: String,
                       val title: String,
                       val year: String,
                       val type: String,
                       val posterUrl: String,
                       val plot: String,
                       val imdbRating: String,
                       val genre: String) : Serializable