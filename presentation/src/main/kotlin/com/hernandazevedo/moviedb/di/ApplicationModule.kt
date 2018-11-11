package com.hernandazevedo.moviedb.di

import android.app.Application
import android.content.Context
import com.hernandazevedo.moviedb.data.LocalDatabase
import com.hernandazevedo.moviedb.data.api.interfaces.ApiRepository
import com.hernandazevedo.moviedb.data.dao.MovieEntityDao
import com.hernandazevedo.moviedb.data.datasource.MovieDataSource
import com.hernandazevedo.moviedb.data.datasource.MovieDataSourceImpl
import com.hernandazevedo.moviedb.data.repository.MovieRepositoryImpl
import com.hernandazevedo.moviedb.domain.repository.MovieRepository
import com.hernandazevedo.moviedb.view.Navigator
import com.hernandazevedo.moviedb.view.NavigatorImpl
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
    fun provideMovieDataSource(apiRepository: ApiRepository,
                               movieEntityDao: MovieEntityDao): MovieDataSource {
        return MovieDataSourceImpl(apiRepository, movieEntityDao)
    }

    @Singleton
    @Provides
    fun providesMovieEntityDao(context: Context): MovieEntityDao = LocalDatabase.getInstance(context).movieEntityDao()

    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return NavigatorImpl()
    }
}