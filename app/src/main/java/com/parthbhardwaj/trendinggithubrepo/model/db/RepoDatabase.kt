package com.parthbhardwaj.trendinggithubrepo.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RepoTable::class), version = 1)
abstract class RepoDatabase: RoomDatabase() {

    abstract fun repoDao(): RepoDao
}