package com.hernandazevedo.moviedb.view.details

import android.arch.lifecycle.MutableLiveData
import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.MovieDetail
import com.hernandazevedo.moviedb.data.Logger
import com.hernandazevedo.moviedb.domain.usecase.base.BaseUseCase
import com.hernandazevedo.moviedb.domain.usecase.request.DeleteMovieFromLocalDatabaseRequest
import com.hernandazevedo.moviedb.domain.usecase.request.GetMovieDetailsRequest
import com.hernandazevedo.moviedb.domain.usecase.request.SaveMovieToLocalDatabaseRequest
import com.hernandazevedo.moviedb.util.UseCaseHandler
import com.hernandazevedo.moviedb.view.base.BaseViewModel
import com.hernandazevedo.moviedb.view.base.Resource

class DetailsViewModel(
    val getMovieDetailsUseCase: BaseUseCase<GetMovieDetailsRequest, MovieDetail>,
    val saveMovieToLocalDatabaseUseCase: BaseUseCase<SaveMovieToLocalDatabaseRequest, Long>,
    val deleteMovieFromLocalDatabaseUseCase: BaseUseCase<DeleteMovieFromLocalDatabaseRequest, Long>
) : BaseViewModel() {

    val responseGetMovieDetails: MutableLiveData<Resource<MovieDetail>> = MutableLiveData()
    val rsponseFavoriteAction: MutableLiveData<Resource<Any>> = MutableLiveData()

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
        val useCaseExecute = if (checked) {
            Logger.d("Saving movie imdbID ${movie.imdbID}")
            val requestValues = SaveMovieToLocalDatabaseRequest(movie)
            UseCaseHandler.execute(saveMovieToLocalDatabaseUseCase, requestValues)
        } else {
            Logger.d("Removing movie imdbID ${movie.imdbID}")
            val requestValues = DeleteMovieFromLocalDatabaseRequest(movie.imdbID)
            UseCaseHandler.execute(deleteMovieFromLocalDatabaseUseCase, requestValues)
        }

        compositeDisposable.add(
            useCaseExecute
                .subscribe({
                    Logger.d("Success favoriteAction $checked")
                    responseGetMovieDetails.value = Resource.success()
                }, {
                    Logger.d("Error favoriteAction $checked")
                    responseGetMovieDetails.value = Resource.error(it)
                }))
    }
}