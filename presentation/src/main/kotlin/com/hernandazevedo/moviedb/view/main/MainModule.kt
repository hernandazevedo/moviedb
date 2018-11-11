package com.hernandazevedo.moviedb.view.main

import com.hernandazevedo.moviedb.domain.repository.MovieRepository
import com.hernandazevedo.moviedb.domain.usecase.FetchMyFavoritesUseCase
import com.hernandazevedo.moviedb.domain.usecase.SearchMovieUseCase
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    fun provideMainViewModel(searchMovieUseCase: SearchMovieUseCase,
                             fetchMyFavoritesUseCase: FetchMyFavoritesUseCase):
            MainViewModel {
        return MainViewModel(searchMovieUseCase, fetchMyFavoritesUseCase)
    }

    @Provides
    fun providesSearchMovieUseCase(movieRepository: MovieRepository): SearchMovieUseCase {
        return SearchMovieUseCase(movieRepository)
    }

    @Provides
    fun providesFetchMyFavoritesUseCase(movieRepository: MovieRepository): FetchMyFavoritesUseCase {
        return FetchMyFavoritesUseCase(movieRepository)
    }
}