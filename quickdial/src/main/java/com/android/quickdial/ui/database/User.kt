package com.android.quickdial.ui.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author rosetta
 * @date 2021/01/08
 */

@Entity
data class User(
    var name: String,
    var phone: String
) {
    @PrimaryKey
    var id = 0
}