package com.hernandazevedo.moviedb.data.mapper

import com.hernandazevedo.moviedb.Constants
import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.data.base.BaseMapper
import com.hernandazevedo.moviedb.data.entity.MovieEntity

object MovieEntityMapper : BaseMapper<Movie, MovieEntity>() {
    override fun transformFrom(source: MovieEntity): Movie {
        return Movie(id = source.id,
            title = source.title,
            imdbID = source.imdbID,
            posterUrl = source.posterUrl,
            year = source.year,
            type = source.type,
            favored = source.favored)
    }

    override fun transformTo(source: Movie): MovieEntity {
        return MovieEntity(
            id = source.id ?: Constants.NO_VALUE_LONG,
            title = source.title,
            imdbID = source.imdbID,
            posterUrl = source.posterUrl,
            year = source.year,
            type = source.type,
            favored = source.favored)
    }
}