package com.android.lyc.model

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

/**
 * @author rosetta
 * @date 2020/02/15
 */
class PeopleModel {
    val firstName = ObservableField<String>("first")
    val lastName = ObservableField<String>("last")
    val age = ObservableInt(10)
}