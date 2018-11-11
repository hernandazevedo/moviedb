package com.hernandazevedo.moviedb.view.details

import com.hernandazevedo.moviedb.domain.repository.MovieRepository
import com.hernandazevedo.moviedb.domain.usecase.GetMovieDetailsUseCase
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {
    @Provides
    fun provideDetailsViewModel(getMovieDetailsUseCase: GetMovieDetailsUseCase):
            DetailsViewModel {
        return DetailsViewModel(getMovieDetailsUseCase)
    }

    @Provides
    fun provideGetMovieDetailsUseCase(movieRepository: MovieRepository):
            GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(movieRepository)
    }
}