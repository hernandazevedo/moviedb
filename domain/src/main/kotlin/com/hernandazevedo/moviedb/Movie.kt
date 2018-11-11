package com.hernandazevedo.moviedb

import java.io.Serializable

data class Movie(val id: Long? = null,
                 val imdbID: String,
                 val title: String,
                 val year: String,
                 val type: String,
                 val posterUrl: String,
                 var favored: Boolean = false) : Serializable