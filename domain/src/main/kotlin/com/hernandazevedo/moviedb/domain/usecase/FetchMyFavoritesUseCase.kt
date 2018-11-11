package com.hernandazevedo.moviedb.domain.usecase

import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.domain.repository.MovieRepository
import com.hernandazevedo.moviedb.domain.usecase.base.BaseRequestValues
import com.hernandazevedo.moviedb.domain.usecase.base.BaseUseCase
import io.reactivex.Observable

class FetchMyFavoritesUseCase(val movieRepository: MovieRepository) : BaseUseCase<BaseRequestValues, List<Movie>>() {
    override fun executeUseCase(requestValues: BaseRequestValues?): Observable<List<Movie>> {
        return@executeUseCase movieRepository.findSavedMoviesOnLocalDatabase()
    }
}