package com.hernandazevedo.moviedb.di

import com.hernandazevedo.moviedb.view.details.DetailsActivity
import com.hernandazevedo.moviedb.view.details.DetailsModule
import com.hernandazevedo.moviedb.view.main.MainActivity
import com.hernandazevedo.moviedb.view.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun buildMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(DetailsModule::class)])
    abstract fun buildDetailsActivity(): DetailsActivity
}