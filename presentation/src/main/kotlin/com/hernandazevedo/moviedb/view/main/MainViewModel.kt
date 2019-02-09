package com.hernandazevedo.moviedb.view.main

import android.arch.lifecycle.MutableLiveData
import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.data.Logger
import com.hernandazevedo.moviedb.domain.usecase.base.BaseRequestValues
import com.hernandazevedo.moviedb.domain.usecase.base.BaseUseCase
import com.hernandazevedo.moviedb.domain.usecase.request.SearchMovieRequest
import com.hernandazevedo.moviedb.util.UseCaseHandler
import com.hernandazevedo.moviedb.view.base.BaseViewModel
import com.hernandazevedo.moviedb.view.base.Either

open class MainViewModel(val searchMovieUseCase: BaseUseCase<SearchMovieRequest, List<Movie>>,
                         val fetchMyFavoritesUseCase: BaseUseCase<BaseRequestValues, List<Movie>>) : BaseViewModel() {
    //Here could be an object created on presentation layer, but to be simple we are using the domain model.
    val responseSearchMovie: MutableLiveData<Either<Throwable, List<Movie>>> = MutableLiveData()

    fun searchMovie(title: String) {
        Logger.d("Starting searchMovie - $title")
        val requestValues = SearchMovieRequest(title)
        val useCaseExecute = UseCaseHandler.execute(searchMovieUseCase, requestValues)
        compositeDisposable.add(
            useCaseExecute
                .subscribe({
                    Logger.d("Success searching movies for title $title")
                    responseSearchMovie.value = Either.Right(it)
                }, {
                    Logger.d("Left searching movies for title $title")
                    responseSearchMovie.value = Either.Left(it)
                }))
    }

    fun fetchMyfavorites() {
        Logger.d("Starting fetchMyfavorites")

        val useCaseExecute = UseCaseHandler.execute(fetchMyFavoritesUseCase)
        compositeDisposable.add(
            useCaseExecute
                .subscribe({
                    Logger.d("Success searching favorited movies")
                    responseSearchMovie.value = Either.Right(it)
                }, {
                    Logger.d("Left searching favorited movies")
                    responseSearchMovie.value = Either.Left(it)
                }))
    }
}