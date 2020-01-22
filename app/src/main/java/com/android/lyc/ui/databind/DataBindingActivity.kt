package com.android.lyc.ui.databind

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.lyc.R
import com.android.lyc.databinding.DataBingActivityBinding
import com.android.lyc.model.UserModel
import com.android.lyc.util.ClickUtil

/**
 * @author yc.li09  <a href="yongchun.li@ucarinc.com"></>
 * @date 2020/01/21 16:20
 */
class DataBindingActivity:AppCompatActivity (), View.OnClickListener {
    /**
     * auto generated  path:build/generated/..../package/databinding/xxx.java
     */
    private lateinit var binding: DataBingActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.data_bing_activity)

        binding.title = "Data Binding Example"
        binding.func1 = "1. text & click"
        binding.onClickListener = this

        var model = UserModel("user1", 10)
        var bean = com.android.lyc.bean.UserModel("HelleWorld")
        binding.data1 = model
        binding.data2 = bean
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fun1_btn -> {
                ClickUtil.processClick(view, "fun_1 click")
                binding.func1 = "clicked"
            }

        }
    }
}