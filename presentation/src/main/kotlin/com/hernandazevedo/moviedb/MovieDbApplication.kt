package com.hernandazevedo.moviedb

import android.app.Activity
import android.app.Application
import com.hernandazevedo.moviedb.data.Logger
import com.hernandazevedo.moviedb.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class MovieDbApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        Logger.init()
        injectDependencies()
        Logger.d("Starting Application")
    }

    private fun injectDependencies() {
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector
}