package com.hernandazevedo.moviedb.di

import android.app.Application
import android.content.Context
import com.hernandazevedo.moviedb.data.LocalDatabase
import com.hernandazevedo.moviedb.data.dao.MovieEntityDao
import com.hernandazevedo.moviedb.data.datasource.MovieDataSource
import com.hernandazevedo.moviedb.data.repository.MovieRepositoryImpl
import com.hernandazevedo.moviedb.domain.repository.MovieRepository
import com.hernandazevedo.moviedb.fake.datasource.FakeMovieDataSource
import com.hernandazevedo.moviedb.view.Navigator
import com.hernandazevedo.moviedb.view.NavigatorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModuleTest {

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
    fun provideMovieDataSource(): MovieDataSource {
        return FakeMovieDataSource()
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