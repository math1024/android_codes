package com.android.lyc.data

import android.util.Log
import androidx.paging.LivePagedListBuilder
import com.android.lyc.api.GithubService
import com.android.lyc.db.GithubLocalCache
import com.android.lyc.model.RepoBoundaryCallback
import com.android.lyc.model.RepoSearchResult

/**
 * @author rosetta
 * @date 2020/03/03
 */
class GithubRepository(
    private val service: GithubService,
    private val cache: GithubLocalCache
)  {
    /**
     * Search repositories whose names match the query.
     */
    fun search(query: String): RepoSearchResult {
        Log.d("GithubRepository", "New query: $query")

        // Get data source factory from the local cache
        val dataSourceFactory = cache.reposByName(query)

        // every new query creates a new BoundaryCallback
        // The BoundaryCallback will observe when the user reaches to the edges of
        // the list and update the database with extra data
        val boundaryCallback = RepoBoundaryCallback(query, service, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()

        // Get the network errors exposed by the boundary callback
        return RepoSearchResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}