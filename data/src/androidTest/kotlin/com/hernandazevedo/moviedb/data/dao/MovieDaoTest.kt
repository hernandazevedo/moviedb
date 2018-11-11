package com.hernandazevedo.moviedb.data.dao

import com.hernandazevedo.moviedb.data.base.BaseDatabaseTest
import com.hernandazevedo.moviedb.data.entity.MovieEntity
import org.junit.Before
import org.junit.Test

class MovieDaoTest : BaseDatabaseTest() {

    private lateinit var movieEntityDao: MovieEntityDao

    private var MOVIE_MOCK = MovieEntity(
        id = 100L, // Override Room @autoGenerate Id
        imdbID = "imdbId",
        title = "Guardians of Galaxy",
        year = "2018",
        type = "movie",
        posterUrl = "posterUrl")

    @Before
    override fun setup() {
        super.setup()
        movieEntityDao = localDatabase.movieEntityDao()
    }

    @Test
    fun createPaymentAndFindByIdSuccess() {

        movieEntityDao.insertMovie(MOVIE_MOCK)

        movieEntityDao.findMovieById(MOVIE_MOCK.id)
            .test()
            .assertNoErrors()
            .assertValue(MOVIE_MOCK)
            .assertComplete()
    }
}