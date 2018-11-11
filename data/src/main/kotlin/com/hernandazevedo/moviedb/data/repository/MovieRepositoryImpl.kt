package com.hernandazevedo.moviedb.data.repository

import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.MovieDetail
import com.hernandazevedo.moviedb.data.datasource.MovieDataSource
import com.hernandazevedo.moviedb.data.mapper.MovieDetailMapper
import com.hernandazevedo.moviedb.data.mapper.MovieEntityMapper
import com.hernandazevedo.moviedb.data.mapper.MovieMapper
import com.hernandazevedo.moviedb.domain.repository.MovieRepository
import io.reactivex.Observable
import java.lang.Exception

class MovieRepositoryImpl(var movieDataSource: MovieDataSource) : MovieRepository {
    override fun findMovieByTitle(title: String): Observable<List<Movie>> {
        return movieDataSource.findMovieByTitle(title).map { MovieMapper.transformToList(it.search) }
    }

    override fun findMovieByImdbID(imdbID: String): Observable<MovieDetail> {
        return movieDataSource.findMovieByImdbId(imdbID).map { MovieDetailMapper.transformTo(it) }.doOnNext {
            try {
                val movieEntity = movieDataSource.findMovieOnLocalDatabaseByImdbId(imdbID)
                it.favored = movieEntity.favored
            } catch (e: Exception) {
                it.favored = false
            }
        }
    }

    override fun saveMovieToLocalDatabase(movie: Movie): Observable<Long> {
        return movieDataSource.saveMovieToLocalDatabase(movie)
    }

    override fun deleteMovieFromLocalDatabase(imdbID: String): Observable<Long> {
        return movieDataSource.deleteMovieFromLocalDatabase(imdbID)
    }

    override fun findSavedMoviesOnLocalDatabase(): Observable<List<Movie>> {
        return movieDataSource.findSavedMoviesOnLocalDatabase().map { MovieEntityMapper.transformFromList(it) }
    }
}