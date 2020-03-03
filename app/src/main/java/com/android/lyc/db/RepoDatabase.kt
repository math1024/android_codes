package com.android.lyc.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.lyc.model.Repo

/**
 * @author rosetta
 * @date 2020/03/03
 */
@Database(
    entities = [Repo::class],
    version = 1,
    exportSchema = false
)
abstract class RepoDatabase : RoomDatabase() {
    abstract fun reposDao(): RepoDao

    companion object {

        @Volatile
        private var INSTANCE: RepoDatabase? = null

        fun getInstance(context: Context): RepoDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                RepoDatabase::class.java, "Github.db")
                .build()
    }
}