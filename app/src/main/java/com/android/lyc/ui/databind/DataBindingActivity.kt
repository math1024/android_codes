package com.android.lyc.ui.databind

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.lyc.R
import com.android.lyc.databinding.DatabingActivityBinding

/**
 * @author yc.li09  <a href="yongchun.li@ucarinc.com"></>
 * @date 2020/01/21 16:20
 */
class DataBindingActivity:AppCompatActivity (), View.OnClickListener {
    /**
     * auto generated  path:build/generated/..../package/databinding/xxx.java
     */
    private lateinit var binding: DatabingActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.databing_activity)

//        binding.textStr = "DatabingActivityBinding是自动生成的"
//        binding.onClickListener = this
    }

    override fun onClick(view: View?) {
//        binding.textStr = "点击设置的数据"
    }
}