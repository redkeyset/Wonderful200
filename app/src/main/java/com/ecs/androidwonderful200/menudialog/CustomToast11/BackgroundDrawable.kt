package com.ecs.androidwonderful200.menudialog.CustomToast11

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorInt
import android.util.TypedValue


/**
 * 作者：RedKeyset on 2019/8/2 11:02
 * 邮箱：redkeyset@aliyun.com
 */

class BackgroundDrawable(@ColorInt color: Int, context: Context) : Drawable() {
    private val paint: Paint
    private val mContext: Context

    init {
        mContext = context.getApplicationContext()
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.setColor(color)
        paint.setDither(true)
        paint.setStyle(Paint.Style.FILL)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun draw(canvas: Canvas) {
        val width = canvas.getWidth()
        val height = canvas.getHeight()
        canvas.drawRoundRect(0F, 0F, width.toFloat(), height.toFloat(), dp2px(20).toFloat(), dp2px(20).toFloat(), paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.setAlpha(alpha)
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.setColorFilter(colorFilter)
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    private fun dp2px(values: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, values.toFloat(),
                mContext.getResources().getDisplayMetrics()).toInt()
    }
}