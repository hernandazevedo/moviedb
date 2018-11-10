package com.hernandazevedo.moviedb.di

import android.app.Application
import com.hernandazevedo.moviedb.MovieDbApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class),
    (AndroidSupportInjectionModule::class),
    (ApiModule::class),
    (ApplicationModule::class),
    (ActivityBuilder::class)])
abstract class ApplicationComponent : AndroidInjector<MovieDbApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}