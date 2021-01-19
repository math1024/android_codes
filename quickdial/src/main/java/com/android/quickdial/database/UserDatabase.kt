package com.android.quickdial.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * @author rosetta
 * @date 2021/01/08
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase() : RoomDatabase() {

    abstract fun userDao(): UserDao


    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_db"
                ).allowMainThreadQueries().addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            println("db create")
//                           getDatabase(context).userDao().all
                        }
                    }
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}