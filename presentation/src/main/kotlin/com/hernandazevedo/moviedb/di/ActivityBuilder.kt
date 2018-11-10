package com.hernandazevedo.moviedb.di

import com.hernandazevedo.moviedb.view.main.MainActivity
import com.hernandazevedo.moviedb.view.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun buildMainActivity(): MainActivity
}