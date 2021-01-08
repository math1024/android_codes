package com.android.quickdial.ui.database

import androidx.room.*
import io.reactivex.Completable

/**
 * @author rosetta
 * @date 2021/01/08
 */
@Dao
interface UserDao {
    //查询user表中所有数据
    @get:Query("SELECT * FROM user")
    val all: List<User?>?


    @Query("SELECT * FROM User LIMIT 1")
    fun findUser(): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Completable

    @Delete
    fun delete(vararg users: User?)

    @Query("DELETE FROM User")
    fun deleteAllUser()
}