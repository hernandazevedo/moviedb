package com.hernandazevedo.moviedb.domain.usecase

import com.hernandazevedo.moviedb.domain.exceptions.RequestValuesNotImplementedException
import com.hernandazevedo.moviedb.domain.repository.MovieRepository
import com.hernandazevedo.moviedb.domain.usecase.base.BaseUseCase
import com.hernandazevedo.moviedb.domain.usecase.request.SaveMovieToLocalDatabaseRequest
import io.reactivex.Observable

class SaveMovieToLocalDatabaseUseCase(val movieRepository: MovieRepository) :
    BaseUseCase<SaveMovieToLocalDatabaseRequest, Long>() {
    override fun executeUseCase(requestValues: SaveMovieToLocalDatabaseRequest?): Observable<Long> {
        requestValues?.let {
            requestValues.movie.favored = true
            return@executeUseCase movieRepository.saveMovieToLocalDatabase(requestValues.movie)
        }
        return Observable.error(RequestValuesNotImplementedException())
    }
}