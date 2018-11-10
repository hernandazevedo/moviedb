package com.hernandazevedo.moviedb.view.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.R
import com.hernandazevedo.moviedb.data.Logger
import com.hernandazevedo.moviedb.data.util.NativeUtils
import com.hernandazevedo.moviedb.getFactoryViewModel
import com.hernandazevedo.moviedb.view.base.BaseActivity
import com.hernandazevedo.moviedb.view.base.Resource
import com.hernandazevedo.moviedb.view.base.Status
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = getFactoryViewModel { mainViewModel }
        setContentView(R.layout.activity_main)
        subscribToSearchMovie()

        //FIXME Remove this test for the api call
        mainViewModel.searchMovie("Guardian*")
        // Example of a call to a native method
        sample_text.text = NativeUtils().stringFromJNI()
    }

    private fun subscribToSearchMovie() {
        mainViewModel.responseSearchMovie.observe(this,
            Observer<Resource<List<Movie>>> {
                when (it?.status) {
                    Status.SUCCESS -> {
                        Logger.d("Success finding movie")
                    }
                    Status.ERROR -> {
                        Logger.d("Error finding movie")
                        //TODO show the error to the user
                        it.throwable?.printStackTrace()
                    }
                }
            })
    }
}
