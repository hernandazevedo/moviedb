package com.hernandazevedo.moviedb.domain.usecase

import com.hernandazevedo.moviedb.MovieDetail
import com.hernandazevedo.moviedb.domain.exceptions.RequestValuesNotImplementedException
import com.hernandazevedo.moviedb.domain.repository.MovieRepository
import com.hernandazevedo.moviedb.domain.usecase.base.BaseUseCase
import com.hernandazevedo.moviedb.domain.usecase.request.GetMovieDetailsRequest
import io.reactivex.Observable

class GetMovieDetailsUseCase(val movieRepository: MovieRepository) :
    BaseUseCase<GetMovieDetailsRequest, MovieDetail>() {
    override fun executeUseCase(requestValues: GetMovieDetailsRequest?): Observable<MovieDetail> {
        requestValues?.let {
            return@executeUseCase movieRepository.findMovieByImdbID(requestValues.imdbID)
        }
        return Observable.error(RequestValuesNotImplementedException())
    }
}