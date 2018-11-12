package com.hernandazevedo.moviedb.domain.usecase

import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.domain.exceptions.RequestValuesNotImplementedException
import com.hernandazevedo.moviedb.domain.repository.MovieRepository
import com.hernandazevedo.moviedb.domain.usecase.base.BaseUseCase
import com.hernandazevedo.moviedb.domain.usecase.request.SearchMovieRequest
import io.reactivex.Observable

class SearchMovieUseCase(val movieRepository: MovieRepository) : BaseUseCase<SearchMovieRequest, List<Movie>>() {
    override fun executeUseCase(requestValues: SearchMovieRequest?): Observable<List<Movie>> {
        requestValues?.let {
            return@executeUseCase movieRepository.findMovieByTitle(requestValues.title)
        }
        return Observable.error(RequestValuesNotImplementedException())
    }
}