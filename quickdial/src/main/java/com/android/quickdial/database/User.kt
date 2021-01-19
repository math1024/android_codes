package com.android.quickdial.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author rosetta
 * @date 2021/01/08
 */

@Entity(tableName = "users")
data class User(
    var name: String,
    var phone: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}