package com.hernandazevedo.moviedb.data

import hernandazevedo.com.data.BuildConfig
import timber.log.Timber

object Logger {
    private val UNIQUE_TAG = "[MOVIE_DB]"

    fun d(msg: String) {
        Timber.tag(UNIQUE_TAG).d(msg)
    }

    fun e(e: String) {
        Timber.tag(UNIQUE_TAG).e(e)
    }

    fun e(e: Throwable) {
        Timber.tag(UNIQUE_TAG).e(e)
    }

    fun e(msg: String, e: Throwable) {
        Timber.tag(UNIQUE_TAG).e(msg, e)
    }

    fun init() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}