package com.hernandazevedo.moviedb.view.details

import android.arch.lifecycle.MutableLiveData
import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.MovieDetail
import com.hernandazevedo.moviedb.data.Logger
import com.hernandazevedo.moviedb.domain.usecase.base.BaseUseCase
import com.hernandazevedo.moviedb.domain.usecase.request.GetMovieDetailsRequest
import com.hernandazevedo.moviedb.util.UseCaseHandler
import com.hernandazevedo.moviedb.view.base.BaseViewModel
import com.hernandazevedo.moviedb.view.base.Resource

class DetailsViewModel(val getMovieDetailsUseCase: BaseUseCase<GetMovieDetailsRequest, MovieDetail>) : BaseViewModel() {

    val responseGetMovieDetails: MutableLiveData<Resource<MovieDetail>> = MutableLiveData()

    fun getMovieDetails(imdbID: String) {
        Logger.d("Starting getMovieDetails - $imdbID")
        val requestValues = GetMovieDetailsRequest(imdbID)
        val useCaseExecute = UseCaseHandler.execute(getMovieDetailsUseCase, requestValues)
        compositeDisposable.add(
            useCaseExecute
                .subscribe({
                    Logger.d("Success searching movies for imdbID $imdbID")
                    responseGetMovieDetails.value = Resource.success(it)
                }, {
                    Logger.d("Error searching movies for imdbID $imdbID")
                    responseGetMovieDetails.value = Resource.error(it)
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