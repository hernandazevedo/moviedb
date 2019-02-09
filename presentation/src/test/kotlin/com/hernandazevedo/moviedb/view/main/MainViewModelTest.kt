package com.hernandazevedo.moviedb.view.main

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.domain.usecase.base.BaseRequestValues
import com.hernandazevedo.moviedb.domain.usecase.base.BaseUseCase
import com.hernandazevedo.moviedb.domain.usecase.request.SearchMovieRequest
import com.hernandazevedo.moviedb.rx.RxImmediateSchedulerRule
import com.hernandazevedo.moviedb.view.base.Either
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    private val searchMovieUseCase: BaseUseCase<SearchMovieRequest, List<Movie>> = mock()
    private val fetchMyFavoritesUseCase: BaseUseCase<BaseRequestValues, List<Movie>> = mock()

    private lateinit var mainViewModel: MainViewModel

    @Rule
    @JvmField
    var rxSchedulersOverrideRule = RxImmediateSchedulerRule()

    private val fakeTitleSearch = "anyTitle"
    private val fakeMovieList: List<Movie> = listOf(
        Movie(imdbID = "imdbID",
            title = "title",
            year = "1999",
            type = "movie",
            posterUrl = "posterURL")
    )

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        mainViewModel = MainViewModel(
            searchMovieUseCase,
            fetchMyFavoritesUseCase)

        given(searchMovieUseCase.executeUseCase(any())).willReturn(Observable.just(fakeMovieList))
        given(searchMovieUseCase.run()).willReturn(Observable.just(fakeMovieList))

        given(fetchMyFavoritesUseCase.executeUseCase(any())).willReturn(Observable.just(fakeMovieList))
        given(fetchMyFavoritesUseCase.run()).willReturn(Observable.just(fakeMovieList))
    }

    @Test
    fun shouldSearchMovieTest() {
        val expectedResult: MutableLiveData<Either<Throwable, List<Movie>>> = MutableLiveData()
        expectedResult.value = Either.Right(fakeMovieList)
        mainViewModel.searchMovie(fakeTitleSearch)
        Assert.assertEquals(expectedResult.value, mainViewModel.responseSearchMovie.value)
    }
}