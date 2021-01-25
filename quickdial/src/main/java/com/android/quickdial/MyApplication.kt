package com.android.quickdial

import android.app.Application
import android.content.Context
import com.android.quickdial.database.User
import com.android.quickdial.database.UserDatabase
import com.umeng.commonsdk.UMConfigure

/**
 * @author rosetta
 * @date 2021/01/08
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        UMConfigure.preInit(this, "XXXXX", "dev")
        UMConfigure.setLogEnabled(true);
        UserDatabase.getDatabase(this)
        UMConfigure.init(this, "XXXXX", "dev", 1, "dev_push")
    }
}