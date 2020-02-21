package com.android.lyc.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * @author rosetta
 * @date 2020/02/21
 */
class SeekBarViewModel : ViewModel() {
    var seekBarValue = MutableLiveData<Int>()
}