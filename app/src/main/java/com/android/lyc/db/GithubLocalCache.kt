package com.android.lyc.db

import android.util.Log
import androidx.paging.DataSource
import com.android.lyc.model.Repo
import java.util.concurrent.Executor

/**
 * @author rosetta
 * @date 2020/03/03
 */
class GithubLocalCache (
    private val repoDao: RepoDao,
    private val ioExecutor: Executor) {
    /**
     * Insert a list of repos in the database, on a background thread.
     */
    fun insert(repos: List<Repo>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            Log.d("GithubLocalCache", "inserting ${repos.size} repos")
            repoDao.insert(repos)
            insertFinished()
        }
    }

    /**
     * Request a LiveData<List<Repo>> from the Dao, based on a repo name. If the name contains
     * multiple words separated by spaces, then we're emulating the GitHub API behavior and allow
     * any characters between the words.
     * @param name repository name
     */
    fun reposByName(name: String): DataSource.Factory<Int, Repo> {
        // appending '%' so we can allow other characters to be before and after the query string
        val query = "%${name.replace(' ', '%')}%"
        return repoDao.reposByName(query)
    }
}