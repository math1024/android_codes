package com.android.lyc.util

import android.view.View
import android.widget.Toast


/**
 * @author yc.li09
 * @date 2020/01/22 15:48
 */
object ClickUtil {
    fun processClick(view: View, str:String) {
        Toast.makeText(view.context, str, Toast.LENGTH_SHORT).show()
    }
}