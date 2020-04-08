package com.android.graytheme

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.LinearLayout


/**
 * @author rosetta
 * @date 2020/04/08
 */
class GrayLayout(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val mPaint: Paint = Paint()
    override fun draw(canvas: Canvas) {
        canvas.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG)
        super.draw(canvas)
        canvas.restore()
    }

    // no background will run this method, not run draw()
    override fun dispatchDraw(canvas: Canvas) {
        canvas.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG)
        super.dispatchDraw(canvas)
        canvas.restore()
    }

    init {
        val cm = ColorMatrix()
        cm.setSaturation(0f)
        mPaint.colorFilter = ColorMatrixColorFilter(cm)
    }
}
