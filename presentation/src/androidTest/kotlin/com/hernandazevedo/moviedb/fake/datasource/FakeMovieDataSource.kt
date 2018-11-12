package com.hernandazevedo.moviedb.fake.datasource

import com.hernandazevedo.moviedb.Movie
import com.hernandazevedo.moviedb.data.api.remote.RemoteMovie
import com.hernandazevedo.moviedb.data.api.remote.RemoteMovieDetail
import com.hernandazevedo.moviedb.data.api.remote.RemoteMovieSearch
import com.hernandazevedo.moviedb.data.datasource.MovieDataSource
import com.hernandazevedo.moviedb.data.entity.MovieEntity
import io.reactivex.Observable

class FakeMovieDataSource : MovieDataSource {
    companion object {
        const val FAKE_MOVIE_TITLE = "Guardians of Galaxy"
        const val FAKE_MOVIE_YEAR = "1999"
        //And so on put the rest of the parameters
    }

    private val fakeRemoteMovie = RemoteMovie(
        imdbID = "imdbID",
        title = FAKE_MOVIE_TITLE,
        year = FAKE_MOVIE_YEAR,
        type = "movie",
        posterUrl = "https://m.media-amazon.com/images/M/MV5BMTg2MzI1MTg3OF5BMl5BanBnXkFtZTgwNTU3NDA2MTI@._V1_SX300.jpg"
    )

    private val fakeMovieList: List<RemoteMovie> = listOf(
        fakeRemoteMovie
    )

    private val movieEntity = MovieEntity(
        imdbID = "imdbID",
        title = "Guardians of Galaxy",
        year = "1999",
        type = "movie",
        posterUrl = "https://m.media-amazon.com/images/M/MV5BMTg2MzI1MTg3OF5BMl5BanBnXkFtZTgwNTU3NDA2MTI@._V1_SX300.jpg"
    )

    private val fakeSearch = RemoteMovieSearch(search = fakeMovieList, totalResults = "1", response = "True")
    private val fakeRemoteMovieDetail = RemoteMovieDetail(imdbID = "imdbID",
        title = "title",
        year = "1999",
        type = "movie",
        plot = "plot",
        imdbRating = "8.0",
        genre = "SCI FI",
        posterUrl = "https://m.media-amazon.com/images/M/MV5BMTg2MzI1MTg3OF5BMl5BanBnXkFtZTgwNTU3NDA2MTI@._V1_SX300.jpg"
    )

    override fun findMovieByTitle(title: String): Observable<RemoteMovieSearch> {
        return Observable.just(fakeSearch)
    }

    override fun findMovieByImdbId(imdbID: String): Observable<RemoteMovieDetail> {
        return Observable.just(fakeRemoteMovieDetail)
    }

    override fun saveMovieToLocalDatabase(movie: Movie): Observable<Long> {
        return Observable.just(1L)
    }

    override fun deleteMovieFromLocalDatabase(imdbID: String): Observable<Long> {
        return Observable.just(1L)
    }

    override fun findMovieOnLocalDatabaseByImdbId(imdbID: String): MovieEntity {
        return movieEntity
    }

    override fun findSavedMoviesOnLocalDatabase(): Observable<List<MovieEntity>> {
        return Observable.just(listOf(movieEntity))
    }
}