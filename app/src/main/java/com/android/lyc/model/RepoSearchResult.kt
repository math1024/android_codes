package com.android.lyc.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * @author rosetta
 * @date 2020/03/03
 */
data class RepoSearchResult(
    val data: LiveData<PagedList<Repo>>,
    val networkErrors: LiveData<String>
)