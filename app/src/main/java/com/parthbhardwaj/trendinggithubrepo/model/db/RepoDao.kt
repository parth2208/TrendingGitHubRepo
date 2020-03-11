package com.parthbhardwaj.trendinggithubrepo.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface RepoDao {

    @get:Query("SELECT * FROM repoTable")
    val all: List<RepoTable>

    @Insert
    fun insertAll(vararg posts: RepoTable)
}