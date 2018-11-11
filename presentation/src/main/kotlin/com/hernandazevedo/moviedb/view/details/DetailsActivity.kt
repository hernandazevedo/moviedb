package com.hernandazevedo.moviedb.view.details

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.CheckBox
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.MovieDetail
import com.hernandazevedo.moviedb.R
import com.hernandazevedo.moviedb.data.Logger
import com.hernandazevedo.moviedb.getFactoryViewModel
import com.hernandazevedo.moviedb.view.base.BaseActivity
import com.hernandazevedo.moviedb.view.base.Resource
import com.hernandazevedo.moviedb.view.base.Status
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : BaseActivity() {
    @Inject
    lateinit var detailsViewModel: DetailsViewModel

    companion object {
        val INTENT_MOVIE_SELECTED = "movie"
    }

    val movie by lazy {
        intent.getSerializableExtra(INTENT_MOVIE_SELECTED) as Movie
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsViewModel = getFactoryViewModel { detailsViewModel }
        setContentView(R.layout.activity_details)
        subscribeToSearchMovie()
        populateInfo()
        detailsViewModel.getMovieDetails(movie.imdbID)
    }

    private fun subscribeToSearchMovie() {
        detailsViewModel.responseGetMovieDetails.observe(this,
            Observer<Resource<MovieDetail>> {
                when (it?.status) {
                    Status.SUCCESS -> {
                        Logger.d("Success finding movie details")
                        fillMovieDetail(it.data)
                    }
                    Status.ERROR -> {
                        Logger.d("Error finding movie details")
                        showMessage("Error finding movie details")
                        it.throwable?.printStackTrace()
                    }
                }
            })
    }

    private fun fillMovieDetail(movieDetail: MovieDetail?) {
        movieDetail?.let {
            movieVoteAverage.text = it.imdbRating
            movieGenres.text = it.genre
            movieOverView.text = it.plot
        }
    }

    private fun populateInfo() {
        movie.apply {
            val options = RequestOptions()
                .priority(Priority.HIGH)
                .placeholder(R.drawable.no_image)

            Glide.with(this@DetailsActivity)
                .load(movie.posterUrl)
                .apply(options)
                .into(movieImage)

            movieTitle.text = "# ${movie.title}"
            movieYear.text = year
            movieVoteAverage.text = "'-'"
            favoriteButton.isChecked = favored ?: false

            movieAdultImage.setImageDrawable(getDrawable(R.drawable.ic_clapperboard))
            //TODO
            var strGenres = "Genre"
            movieGenres.text = strGenres
        }
    }

    private fun setupFavAction() {
        favoriteButton.setOnClickListener { v ->
            val checked = (v as CheckBox).isChecked
            detailsViewModel.favoriteAction(checked, movie)
        }
    }
}