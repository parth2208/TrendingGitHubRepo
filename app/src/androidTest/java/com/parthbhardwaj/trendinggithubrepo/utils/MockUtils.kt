package com.parthbhardwaj.trendinggithubrepo.utils

import com.parthbhardwaj.trendinggithubrepo.model.db.RepoTable
import java.util.ArrayList

object MockUtils {

    fun mockModels(): List<RepoTable> {
        val models = ArrayList<RepoTable>()

        val model1 = RepoTable(1, "John", "cloud2020", "null", "springcloud",
            "Java", "", "",
            "", "1234")
        models.add(model1)

        val model2 = RepoTable(2, "Cristiano", "firstlookmedia", "null", "Node.js and mongodb google drive clone",
             "Kotlin", "", "",
            "", "452")
        models.add(model2)

        val model3 = RepoTable(3, "iikira", "JavaGuide", "AndroidTest3", "", "", "", "",
            "Go", "23")
        models.add(model3)

        return models
    }
}