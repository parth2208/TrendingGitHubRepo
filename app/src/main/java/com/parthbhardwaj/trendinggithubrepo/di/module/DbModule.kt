package com.parthbhardwaj.trendinggithubrepo.di.module

import android.app.Application
import androidx.room.Room
import com.parthbhardwaj.trendinggithubrepo.model.db.RepoDatabase
import com.parthbhardwaj.trendinggithubrepo.model.db.RepoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    internal fun provideDb(application: Application): RepoDatabase{
        return Room.databaseBuilder(application,
            RepoDatabase::class.java, "Trending DB").build()
    }

    @Provides
    @Singleton
    internal fun provideRepoTableDao(repoDatabase: RepoDatabase): RepoDao{
        return repoDatabase.repoTableDao()
    }

}