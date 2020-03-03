package com.android.lyc.api

import com.android.lyc.model.Repo
import com.google.gson.annotations.SerializedName

/**
 * @author rosetta
 * @date 2020/03/03
 */
data class RepoSearchResponse (
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<Repo> = emptyList(),
    val nextPage: Int? = null
)