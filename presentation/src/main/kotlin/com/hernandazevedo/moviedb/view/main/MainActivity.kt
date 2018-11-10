package com.hernandazevedo.moviedb.view.main

import android.os.Bundle
import com.hernandazevedo.moviedb.R
import com.hernandazevedo.moviedb.data.util.NativeUtils
import com.hernandazevedo.moviedb.getFactoryViewModel
import com.hernandazevedo.moviedb.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = getFactoryViewModel { mainViewModel }
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        sample_text.text = NativeUtils().stringFromJNI()
    }
}
