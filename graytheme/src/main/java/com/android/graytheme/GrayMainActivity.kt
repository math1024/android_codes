package com.android.graytheme

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity


/**
 * html {filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);-webkit-filter: grayscale(100%);}
 */
class GrayMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gray_main)

        val paint = Paint()
        val cm = ColorMatrix()
        cm.setSaturation(0f)
        paint.colorFilter = ColorMatrixColorFilter(cm)
        window.decorView.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
//        try {
//            if ("FrameLayout" == name) {
//                val count: Int = attrs.attributeCount
//                for (i in 0 until count) {
//                    val attributeName: String = attrs.getAttributeName(i)
//                    val attributeValue: String = attrs.getAttributeValue(i)
//                    if (attributeName == "id") {
//                        val id = attributeValue.substring(1).toInt()
//                        val idVal = resources.getResourceName(id)
//                        if ("android:id/content" == idVal) {
//                            return GrayLayout(context, attrs)
//                        }
//                    }
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
        return super.onCreateView(name, context, attrs)
    }
}
