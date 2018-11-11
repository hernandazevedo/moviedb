package com.hernandazevedo.moviedb.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.hernandazevedo.moviedb.data.dao.MovieEntityDao
import com.hernandazevedo.moviedb.data.entity.MovieEntity

@Database(entities = [
MovieEntity::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun movieEntityDao(): MovieEntityDao

    companion object {

        @Volatile private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        val DATABASE_NAME = "moviedb.db"

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                LocalDatabase::class.java,
                DATABASE_NAME)
                .build()
    }
}