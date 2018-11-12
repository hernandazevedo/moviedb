package com.hernandazevedo.moviedb.runner

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import com.hernandazevedo.moviedb.MovieDbApplicationTest

class ApplicationTestRunner : AndroidJUnitRunner() {

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, MovieDbApplicationTest::class.java.name, context)
    }
}