package com.android.graytheme

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.util.AttributeSet
import java.util.jar.Attributes
import androidx.appcompat.widget.AppCompatImageView as AppCompatImageView

/**
 * @author rosetta
 * @date 2020/04/08
 */
class GrayImageView(context: Context, attributeSet: AttributeSet)
    : AppCompatImageView(context, attributeSet) {
    var paint: Paint = Paint()

    init{

        var cm: ColorMatrix  = ColorMatrix()
        cm.setSaturation(0F)
       paint.colorFilter = ColorMatrixColorFilter(cm)
    }

    override fun draw(canvas: Canvas) {
        canvas.saveLayer(null, paint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
    }
}