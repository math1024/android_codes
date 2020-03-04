package com.android.lyc.ui.paging

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.android.lyc.api.GithubService
import com.android.lyc.data.GithubRepository
import com.android.lyc.db.GithubLocalCache
import com.android.lyc.db.RepoDatabase
import java.util.concurrent.Executors

/**
 * @author rosetta
 * @date 2020/03/04
 */
object Injection {
    /**
     * Creates an instance of [GithubLocalCache] based on the database DAO.
     */
    private fun provideCache(context: Context): GithubLocalCache {
        val database = RepoDatabase.getInstance(context)
        return GithubLocalCache(database.reposDao(), Executors.newSingleThreadExecutor())
    }

    /**
     * Creates an instance of [GithubRepository] based on the [GithubService] and a
     * [GithubLocalCache]
     */
    private fun provideGithubRepository(context: Context): GithubRepository {
        return GithubRepository(GithubService.create(), provideCache(context))
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository(context))
    }
}