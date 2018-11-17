package com.hernandazevedo.moviedb.view.main

import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.runner.AndroidJUnit4
import com.hernandazevedo.moviedb.fake.datasource.FakeMovieDataSource
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    val rule = IntentsTestRule(MainActivity::class.java)

    private val screen = TestMainActivity()

    @Test
    fun shouldBeShownFilterScreenWhenClickOnActionFilter() {
        screen {
            actionFilter {
                isDisplayed()
                click()
            }
            searchMovieButton {
                isDisplayed()
            }
        }
    }

    @Test
    fun shouldSearchMovies() {
        screen {
            actionFilter {
                isDisplayed()
                click()
            }
            searchMovieButton {
                isDisplayed()
            }
            movieTitleEditText.isDisplayed()
            movieTitleEditText.replaceText("Guardians")
            searchMovieButton.click()

            moviesRecyclerView {
                isVisible()
                firstChild<TestMainActivity.Item> {
                    isVisible()
                    movieTitle { hasText(FakeMovieDataSource.FAKE_MOVIE_TITLE) }
                    movieReleaseYear { hasText(FakeMovieDataSource.FAKE_MOVIE_YEAR) }
                }
            }
        }
    }
}