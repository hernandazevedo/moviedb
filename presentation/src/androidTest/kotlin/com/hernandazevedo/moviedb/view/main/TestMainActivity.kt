package com.hernandazevedo.moviedb.view.main

import android.view.View
import com.agoda.kakao.KButton
import com.agoda.kakao.KEditText
import com.agoda.kakao.KRecyclerView
import com.agoda.kakao.Screen
import com.agoda.kakao.KRecyclerItem
import com.agoda.kakao.KTextView
import com.hernandazevedo.moviedb.R
import org.hamcrest.Matcher

open class TestMainActivity : Screen<TestMainActivity>() {
    val actionFilter = KButton { withId(R.id.action_filter) }
    val searchMovieButton = KButton { withId(R.id.searchMovieButton) }
    val movieTitleEditText = KEditText { withId(R.id.movieTitleEditText) }
    val moviesRecyclerView: KRecyclerView = KRecyclerView({
        withId(R.id.moviesRecyclerView)
    }, itemTypeBuilder = {
        itemType(::Item)
    })

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val movieTitle: KTextView = KTextView(parent) { withId(R.id.movieTitle) }
        val movieReleaseYear: KTextView = KTextView(parent) { withId(R.id.movieReleaseYear) }
        //Fill the rest of the components and so on
    }
}