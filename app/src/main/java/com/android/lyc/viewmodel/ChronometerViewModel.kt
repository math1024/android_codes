package com.android.lyc.viewmodel

import androidx.annotation.Nullable
import androidx.lifecycle.ViewModel

/**
 * @author rosetta
 * @date 2020/02/19
 */
class ChronometerViewModel: ViewModel(){

    @Nullable
    private var mStartTime: Long? = null

    @Nullable
    fun getStartTime(): Long? {
        return mStartTime
    }

    fun setStartTime(startTime: Long) {
        mStartTime = startTime
    }
}