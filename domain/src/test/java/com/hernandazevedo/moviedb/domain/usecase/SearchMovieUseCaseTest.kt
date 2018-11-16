package com.hernandazevedo.moviedb.domain.usecase

import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.domain.exceptions.RequestValuesNotImplementedException
import com.hernandazevedo.moviedb.domain.repository.MovieRepository
import com.hernandazevedo.moviedb.domain.usecase.request.SearchMovieRequest
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchMovieUseCaseTest {
    private var movieRepository: MovieRepository = mock()
    @InjectMocks
    private lateinit var useCase: SearchMovieUseCase

    private val mockTitle = "anyTitle"
    private val movieMockList = listOf<Movie>(
        Movie(
        id = 1L,
            imdbID = "imdbID",
            title = "title",
            year = "1999",
            type = "movie",
            posterUrl = "posterUrl"))

    @Before
    fun setUp() {
        given(movieRepository.findMovieByTitle(any())).willReturn(Observable.just(movieMockList))
    }

    @Test
    fun successfulExecute() {
        val requestValues = SearchMovieRequest(mockTitle)
        useCase.setRequestValues(requestValues)
        useCase.run()
            .test()
            .assertValue(movieMockList)
            .assertComplete()
            .assertNoErrors()
    }

    @Test()
    fun shouldBeThrowExceptionWhenNotSetRequestValue() {
        useCase.run()
            .test()
            .assertError(RequestValuesNotImplementedException::class.java)
    }
}