package com.hernandazevedo.moviedb.view.main

import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    fun provideMainViewModel():
            MainViewModel {
        return MainViewModel()
    }
}