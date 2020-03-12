package com.parthbhardwaj.trendinggithubrepo.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * entity for the attributes of table drawn from Json response
 */
@Entity(tableName = "repoTable")
data class RepoTable(@field:PrimaryKey(autoGenerate = true)
                     val id: Int,
                     val author:String,
                     val name:String,
                     val avatar:String,
                     val description:String,
                     val language:String? = "language",
                     val languageColor:String? ="#000000",
                     val stars:String,
                     val forks:String,
                     val currentPeriodStars: String
                     )