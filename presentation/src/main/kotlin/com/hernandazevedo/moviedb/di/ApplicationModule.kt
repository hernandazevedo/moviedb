package com.hernandazevedo.moviedb.di

import android.app.Application
import android.content.Context
import com.hernandazevedo.moviedb.data.api.interfaces.ApiRepository
import com.hernandazevedo.moviedb.data.datasource.MovieDataSource
import com.hernandazevedo.moviedb.data.datasource.MovieDataSourceImpl
import com.hernandazevedo.moviedb.data.repository.MovieRepositoryImpl
import com.hernandazevedo.moviedb.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideMovieRepository(movieDataSource: MovieDataSource): MovieRepository {
        return MovieRepositoryImpl(movieDataSource)
    }

    @Provides
    @Singleton
    fun provideMovieDataSource(apiRepository: ApiRepository): MovieDataSource {
        return MovieDataSourceImpl(apiRepository)
    }
}