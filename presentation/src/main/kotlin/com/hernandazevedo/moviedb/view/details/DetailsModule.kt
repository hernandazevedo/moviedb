package com.hernandazevedo.moviedb.view.details

import com.hernandazevedo.moviedb.domain.repository.MovieRepository
import com.hernandazevedo.moviedb.domain.usecase.DeleteMovieFromLocalDatabaseUseCase
import com.hernandazevedo.moviedb.domain.usecase.GetMovieDetailsUseCase
import com.hernandazevedo.moviedb.domain.usecase.SaveMovieToLocalDatabaseUseCase
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {
    @Provides
    fun provideDetailsViewModel(
        getMovieDetailsUseCase: GetMovieDetailsUseCase,
        saveMovieToLocalDatabaseUseCase: SaveMovieToLocalDatabaseUseCase,
        deleteMovieFromLocalDatabaseUseCase: DeleteMovieFromLocalDatabaseUseCase
    ):
            DetailsViewModel {
        return DetailsViewModel(
            getMovieDetailsUseCase,
            saveMovieToLocalDatabaseUseCase,
            deleteMovieFromLocalDatabaseUseCase
        )
    }

    @Provides
    fun provideGetMovieDetailsUseCase(movieRepository: MovieRepository):
            GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(movieRepository)
    }

    @Provides
    fun provideSaveMovieToLocalDatabaseUseCase(movieRepository: MovieRepository):
            SaveMovieToLocalDatabaseUseCase {
        return SaveMovieToLocalDatabaseUseCase(movieRepository)
    }

    @Provides
    fun provideDeleteMovieFromLocalDatabaseUseCase(movieRepository: MovieRepository):
            DeleteMovieFromLocalDatabaseUseCase {
        return DeleteMovieFromLocalDatabaseUseCase(movieRepository)
    }
}