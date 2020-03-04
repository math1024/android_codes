package com.android.lyc.ui.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.lyc.data.GithubRepository
import com.android.lyc.viewmodel.SearchRepoViewModel

/**
 * @author rosetta
 * @date 2020/03/04
 */
class ViewModelFactory(private val repository: GithubRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchRepoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchRepoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}