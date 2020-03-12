package com.parthbhardwaj.trendinggithubrepo.databaseTest

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.parthbhardwaj.trendinggithubrepo.model.db.RepoDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.security.AccessController.getContext

@RunWith(AndroidJUnit4::class)
abstract class DBTest {

    protected lateinit var db: RepoDatabase

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().targetContext,
            RepoDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        db.close()
    }
}