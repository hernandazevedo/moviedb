package com.hernandazevedo.moviedb

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.Button

fun Button.enable() {
    this.isEnabled = true
}

fun Button.disable() {
    this.isEnabled = false
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

inline fun <reified T : ViewModel> LifecycleOwner.getFactoryViewModel(crossinline factory: () -> T): T {
    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }
    return when {
        this is FragmentActivity -> ViewModelProviders.of(this, vmFactory)[T::class.java]
        this is Fragment -> ViewModelProviders.of(this, vmFactory)[T::class.java]
        else -> throw IllegalArgumentException("LifecycleOwner not supported")
    }
}
