package com.android.lyc.viewmodel

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


/**
 * @author rosetta
 * @date 2020/02/20
 */
class LiveDataTimerViewModel : ViewModel() {
    private val ONE_SECOND = 1000L

    private val mElapsedTime = MutableLiveData<Long>()

    private var mInitialTime: Long = 0
    private var timer: Timer? = null

    init {
        mInitialTime = SystemClock.elapsedRealtime()
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
                println("LiveDataTimerViewModel $newValue")
            }
        }, ONE_SECOND, ONE_SECOND)
    }

    fun getElapsedTime(): LiveData<Long>? {
        return mElapsedTime
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}

