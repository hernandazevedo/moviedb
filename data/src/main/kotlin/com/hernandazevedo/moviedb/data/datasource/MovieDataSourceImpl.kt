package com.hernandazevedo.moviedb.data.datasource

import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.data.api.interfaces.ApiRepository
import com.hernandazevedo.moviedb.data.api.remote.RemoteMovieDetail
import com.hernandazevedo.moviedb.data.api.remote.RemoteMovieSearch
import com.hernandazevedo.moviedb.data.dao.MovieEntityDao
import com.hernandazevedo.moviedb.data.entity.MovieEntity
import com.hernandazevedo.moviedb.data.mapper.MovieEntityMapper
import io.reactivex.Observable

class MovieDataSourceImpl(var apiRepository: ApiRepository,
                          var movieEntityDao: MovieEntityDao) : MovieDataSource {
    override fun findMovieByTitle(title: String): Observable<RemoteMovieSearch> {
        return apiRepository.findMovieByTitle(title)
    }

    override fun findMovieByImdbId(imdbID: String): Observable<RemoteMovieDetail> {
        return apiRepository.findMovieByImdbId(imdbID)
    }

    override fun findMovieOnLocalDatabaseByImdbId(imdbID: String): MovieEntity {
        return movieEntityDao.findMovieByImdbID(imdbID)
    }

    override fun saveMovieToLocalDatabase(movie: Movie): Observable<Long> {
        return Observable.fromCallable { movieEntityDao.insertMovie(MovieEntityMapper.transformTo(movie)) }
    }

    override fun deleteMovieFromLocalDatabase(imdbID: String): Observable<Long> {
        return Observable.fromCallable {
            movieEntityDao.deleteMovie(imdbID)
            return@fromCallable 1L // One row affected
        }
    }
}