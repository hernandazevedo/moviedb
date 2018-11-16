package com.hernandazevedo.moviedb.domain.usecase

import com.hernandazevedo.moviedb.domain.exceptions.RequestValuesNotImplementedException
import com.hernandazevedo.moviedb.domain.repository.MovieRepository
import com.hernandazevedo.moviedb.domain.usecase.base.BaseUseCase
import com.hernandazevedo.moviedb.domain.usecase.request.DeleteMovieFromLocalDatabaseRequest
import io.reactivex.Observable

class DeleteMovieFromLocalDatabaseUseCase(val movieRepository: MovieRepository) :
    BaseUseCase<DeleteMovieFromLocalDatabaseRequest, Long>() {
    override fun executeUseCase(requestValues: DeleteMovieFromLocalDatabaseRequest?): Observable<Long> {
        requestValues?.let {
            return@executeUseCase movieRepository.deleteMovieFromLocalDatabase(requestValues.imdbId)
        }
        return Observable.error(RequestValuesNotImplementedException())
    }
}