package com.hernandazevedo.moviedb.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.hernandazevedo.moviedb.Constants

@Entity(tableName = MovieEntity.TABLE_NAME,
    indices = [(Index("Id", unique = true))]
)
data class MovieEntity (@PrimaryKey(autoGenerate = true)
                        @ColumnInfo(name = "Id")
                        var id: Long = Constants.NO_VALUE_LONG,
                        @ColumnInfo(name = "imdbID")
                        var imdbID: String = Constants.NO_VALUE_STRING,
                        @ColumnInfo(name = "title")
                        var title: String = Constants.NO_VALUE_STRING,
                        @ColumnInfo(name = "year")
                        var year: String = Constants.NO_VALUE_STRING,
                        @ColumnInfo(name = "type")
                        var type: String = Constants.NO_VALUE_STRING,
                        @ColumnInfo(name = "posterUrl")
                        var posterUrl: String = Constants.NO_VALUE_STRING,
                        @ColumnInfo(name = "favored")
                        var favored: Boolean = false
                        ) {
    companion object {
        const val TABLE_NAME = "Movie"
    }
}