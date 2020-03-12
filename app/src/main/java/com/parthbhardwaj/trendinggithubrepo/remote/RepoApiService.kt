package com.parthbhardwaj.trendinggithubrepo.remote

import com.parthbhardwaj.trendinggithubrepo.model.db.RepoTable
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * API call
 */
interface RepoApiService {

    @GET("/repositories")
    fun getRepositories(): Observable<List<RepoTable>>
}