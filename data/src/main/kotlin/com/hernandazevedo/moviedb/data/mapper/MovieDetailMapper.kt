package com.hernandazevedo.moviedb.data.mapper

import com.hernandazevedo.moviedb.MovieDetail
import com.hernandazevedo.moviedb.data.base.BaseMapper
import com.hernandazevedo.moviedb.data.api.remote.RemoteMovieDetail

object MovieDetailMapper : BaseMapper<RemoteMovieDetail, MovieDetail>() {
    override fun transformFrom(source: MovieDetail): RemoteMovieDetail {
        return RemoteMovieDetail(title = source.title,
            imdbID = source.imdbID,
            posterUrl = source.posterUrl,
            year = source.year,
            type = source.type,
            plot = source.plot,
            imdbRating = source.imdbRating,
            genre = source.genre
            )
    }

    override fun transformTo(source: RemoteMovieDetail): MovieDetail {
        return MovieDetail(title = source.title,
            imdbID = source.imdbID,
            posterUrl = source.posterUrl,
            year = source.year,
            type = source.type,
            plot = source.plot,
            imdbRating = source.imdbRating,
            genre = source.genre)
    }
}