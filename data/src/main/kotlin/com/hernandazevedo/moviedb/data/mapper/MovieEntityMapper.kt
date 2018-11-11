package com.hernandazevedo.moviedb.data.mapper

import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.data.base.BaseMapper
import com.hernandazevedo.moviedb.data.entity.MovieEntity

object MovieEntityMapper : BaseMapper<Movie, MovieEntity>() {
    override fun transformFrom(source: MovieEntity): Movie {
        return Movie(title = source.title,
            imdbID = source.imdbID,
            posterUrl = source.posterUrl,
            year = source.year,
            type = source.type)
    }

    override fun transformTo(source: Movie): MovieEntity {
        return MovieEntity(title = source.title,
            imdbID = source.imdbID,
            posterUrl = source.posterUrl,
            year = source.year,
            type = source.type)
    }
}