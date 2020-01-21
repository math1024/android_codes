package com.android.lyc.ui.databind

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.lyc.R

/**
 * @author yc.li09  <a href="yongchun.li@ucarinc.com"></>
 * @date 2020/01/21 16:20
 */
class DataBindingActivity:AppCompatActivity () {
    private lateinit var binding: ViewDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.databing_activity)
        binding.
    }
}