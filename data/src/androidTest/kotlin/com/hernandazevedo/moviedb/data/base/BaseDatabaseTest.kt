package com.hernandazevedo.moviedb.data.base

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.hernandazevedo.moviedb.data.LocalDatabase
import org.junit.After
import org.junit.Before

abstract class BaseDatabaseTest {
    protected lateinit var localDatabase: LocalDatabase

    @Before
    open fun setup() {
        localDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), LocalDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    @Throws(Exception::class)
    open fun closeDb() {
        localDatabase.close()
    }
}