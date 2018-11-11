package com.hernandazevedo.moviedb.domain.usecase.request

import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.domain.usecase.base.BaseRequestValues

data class SaveMovieToLocalDatabaseRequest(val movie: Movie) : BaseRequestValues