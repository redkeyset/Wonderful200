package com.ecs.androidwonderful200.AndroidControls.AZSort015

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * 作者：RedKeyset on 2019/8/6 11:46
 * 邮箱：redkeyset@aliyun.com
 */

class MySideBar : View {
    internal var onTouchingLetterChangedListener: OnTouchingLetterChangedListener? = null
    //	按住改变背景色
    private var showBkg: Boolean = false
    /**被选中位置 */
    internal var choose = -1
    private val paint = Paint()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (showBkg) {
            canvas.drawColor(Color.parseColor("#ccddFF"))
        }

        val height = height.toFloat()
        val width = width.toFloat()
        //		计算单个字母高度
        val singleHeight = height / b.size.toFloat()
        for (i in b.indices) {
            paint.color = Color.parseColor("#79b900")
            paint.textSize = 30f
            if (i == choose) {
                //				选中的颜色
                paint.color = Color.parseColor("#3399ff")
                //				加粗
                paint.isFakeBoldText = true
            }
            //			设置文本坐标
            val xPos = width / 2 - paint.measureText(b[i]) / 2
            val yPos = singleHeight * i + singleHeight
            canvas.drawText(b[i], xPos, yPos, paint)
            paint.reset()
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val action = event.action
        val y = event.y
        val oldChoose = choose
        val c = (y / height * b.size).toInt()
        val listener = onTouchingLetterChangedListener
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                showBkg = true
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c < b.size) {
                        listener.onTouchingLetterChanged(c)
                        choose = c
                        invalidate()
                    }
                }
            }
            MotionEvent.ACTION_MOVE -> if (oldChoose != c && listener != null) {
                if (c >= 0 && c < b.size) {
                    listener.onTouchingLetterChanged(c)
                    choose = c
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP -> {
                showBkg = false
                choose = -1
                invalidate()
            }
        }
        return true
    }

    //接口 触摸监听
    fun setOnTouchingLetterChangedListener(
            onTouchingLetterChangedListener: OnTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener
    }

    interface OnTouchingLetterChangedListener {
        fun onTouchingLetterChanged(s: Int)
    }

    companion object {
        //右侧内容
        var b = arrayOf("热", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    }
}