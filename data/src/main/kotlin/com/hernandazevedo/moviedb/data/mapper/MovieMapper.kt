package com.hernandazevedo.moviedb.data.mapper

import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.data.base.BaseMapper
import com.hernandazevedo.moviedb.data.remote.RemoteMovie

object MovieMapper : BaseMapper<RemoteMovie, Movie>() {
    override fun transformFrom(source: Movie): RemoteMovie {
        return RemoteMovie(title = source.title,
            imdbID = source.imdbID,
            posterUrl = source.posterUrl,
            year = source.year,
            type = source.type)
    }

    override fun transformTo(source: RemoteMovie): Movie {
        return Movie(title = source.title,
            imdbID = source.imdbID,
            posterUrl = source.posterUrl,
            year = source.year,
            type = source.type)
    }
}