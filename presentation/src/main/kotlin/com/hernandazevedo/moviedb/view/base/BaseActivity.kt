package com.hernandazevedo.moviedb.view.base

import android.app.Activity
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_movies.*

abstract class BaseActivity : DaggerAppCompatActivity() {
    private var snackBar: Snackbar? = null

    fun showMessage(message: String) {
        showSnackBar(moviesRecyclerView, message)
    }

    internal fun showSnackBar(view: View, message: String, duration: Int = Snackbar.LENGTH_LONG) {
        snackBar = Snackbar.make(view, message, duration)
        val snackBarView = snackBar?.view
        snackBarView?.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))

        val snackText = snackBar?.view?.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        snackText?.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        snackBar?.show()
    }

    fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}