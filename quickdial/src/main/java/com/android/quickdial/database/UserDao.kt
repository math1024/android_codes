package com.android.quickdial.database

import androidx.room.*
import io.reactivex.Completable

/**
 * @author rosetta
 * @date 2021/01/08
 */
@Dao
interface UserDao {
    //查询user表中所有数据
    @get:Query("SELECT * FROM users")
    val all: List<User?>?


    @Query("SELECT * FROM users WHERE id= :userId")
    fun findUser(userId: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun delete(vararg users: User?)

    @Query("DELETE FROM users")
    fun deleteAllUser()
}