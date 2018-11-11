package com.hernandazevedo.moviedb.view.main

import android.arch.lifecycle.MutableLiveData
import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.data.Logger
import com.hernandazevedo.moviedb.domain.usecase.base.BaseUseCase
import com.hernandazevedo.moviedb.domain.usecase.request.SearchMovieRequest
import com.hernandazevedo.moviedb.util.UseCaseHandler
import com.hernandazevedo.moviedb.view.base.BaseViewModel
import com.hernandazevedo.moviedb.view.base.Resource

open class MainViewModel(val searchMovieUseCase: BaseUseCase<SearchMovieRequest, List<Movie>>) : BaseViewModel() {
    //Here could be an object created on presentation layer, but to be simple we are using the domain model.
    val responseSearchMovie: MutableLiveData<Resource<List<Movie>>> = MutableLiveData()

    fun searchMovie(title: String) {
        Logger.d("Starting searchMovie - $title")
        val requestValues = SearchMovieRequest(title)
        val useCaseExecute = UseCaseHandler.execute(searchMovieUseCase, requestValues)
        compositeDisposable.add(
            useCaseExecute
                .subscribe({
                    Logger.d("Success searching movies for title $title")
                    responseSearchMovie.value = Resource.success(it)
                }, {
                    Logger.d("Error searching movies for title $title")
                    responseSearchMovie.value = Resource.error(it)
                }))
    }

    fun favoriteAction(checked: Boolean, movie: Movie) {
        if (checked) {
            //TODO save the movie to db
            Logger.d("Saving movie")
        } else {
            //TODO remove the movie from db
            Logger.d("Removing movie")
        }
    }
}