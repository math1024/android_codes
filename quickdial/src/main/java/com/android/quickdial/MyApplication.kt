package com.android.quickdial

import android.app.Application
import com.android.quickdial.database.UserDatabase

/**
 * @author rosetta
 * @date 2021/01/08
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        UserDatabase.getInstance(this)
    }
}