package com.hernandazevedo.moviedb.data.dao
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.hernandazevedo.moviedb.data.entity.MovieEntity
import io.reactivex.Single

@Dao
interface MovieEntityDao {
    @Insert
    fun insertMovie(transaction: MovieEntity): Long

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME} WHERE Id = :movieId")
    fun findMovieById(movieId: Long): Single<MovieEntity>

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME} WHERE imdbID = :imdbID")
    fun findMovieByImdbID(imdbID: String): MovieEntity

    @Query("SELECT * from ${MovieEntity.TABLE_NAME}")
    fun getAllMovies(): Single<List<MovieEntity>>

    @Query("DELETE FROM ${MovieEntity.TABLE_NAME} WHERE imdbID = :imdbId")
    fun deleteMovie(imdbId: String)
}