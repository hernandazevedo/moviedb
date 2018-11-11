package com.hernandazevedo.moviedb.data.repository

import com.hernandazevedo.moviedb.data.api.remote.RemoteMovie
import com.hernandazevedo.moviedb.data.api.remote.RemoteMovieSearch
import com.hernandazevedo.moviedb.data.datasource.MovieDataSource
import com.hernandazevedo.moviedb.data.mapper.MovieMapper
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImplTest {

    private val movieDataSource: MovieDataSource = mock()

    private val fakeMovieList: List<RemoteMovie> = listOf(
        RemoteMovie(imdbID = "imdbID",
            title = "title",
            year = "1999",
            type = "movie",
            posterUrl = "posterURL"))
    private val fakeTitleSearch = "anyTitle"
    private val fakeSearch = RemoteMovieSearch(search = fakeMovieList, totalResults = "1", response = "True")

    @InjectMocks
    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun setUp() {
        given(movieDataSource.findMovieByTitle(fakeTitleSearch)).willReturn(Observable.just(fakeSearch))
    }

    @Test
    fun shouldCallDataSourceFindMovieByTitleSuccess() {
        repository.findMovieByTitle(fakeTitleSearch)
            .test()
            .assertValue {
                it == MovieMapper.transformToList(fakeMovieList)
            }
            .assertNoErrors()
            .assertComplete()

        verify(movieDataSource).findMovieByTitle(fakeTitleSearch)
    }
}