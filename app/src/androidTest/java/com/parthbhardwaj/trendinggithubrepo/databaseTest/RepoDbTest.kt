package com.parthbhardwaj.trendinggithubrepo.databaseTest

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.parthbhardwaj.trendinggithubrepo.utils.MockUtils
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepoDbTest {

    @Test
    fun insertAndReadPostsTest() {
        val repositories =
            MockUtils.mockModels()
        db.repoDao().insertAll(repositories.toTypedArray())

        val storedPosts1 = db.repoDao().completeRepository()
        Assert.assertEquals(1, storedPosts1[0].id)

        val storedPosts2 = db.repoDao().completeRepository()
        Assert.assertEquals(2, storedPosts2[0].id)
    }


    @Test
    fun emptyPostsTest() {
        db.repoDao().insderAll()

        val storedPosts = db.repoDao().completeRepository()
        Assert.assertEquals(0, storedPosts.size.toLong())
    }
}