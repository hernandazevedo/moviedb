package com.hernandazevedo.moviedb

data class Movie(val id: Long? = null,
                 val imdbID: String,
                 val title: String,
                 val year: String,
                 val type: String,
                 val posterUrl: String,
                 var favored: Boolean = false)