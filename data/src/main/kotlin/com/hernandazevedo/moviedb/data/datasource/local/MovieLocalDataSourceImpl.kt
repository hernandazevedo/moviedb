package com.hernandazevedo.moviedb.data.datasource.local

import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.data.dao.MovieEntityDao
import com.hernandazevedo.moviedb.data.mapper.MovieEntityMapper
import io.reactivex.Single

class MovieLocalDataSourceImpl(val movieEntityDao: MovieEntityDao) : MovieLocalDataSource {
    override fun insertMovie(movie: Movie): Single<Long> {
        return Single.fromCallable {
            val movieEntity = MovieEntityMapper.transformTo(movie)
            movieEntityDao.insertMovie(movieEntity)
        }
    }
}