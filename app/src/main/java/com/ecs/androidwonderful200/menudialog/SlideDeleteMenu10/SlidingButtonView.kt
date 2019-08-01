package com.ecs.androidwonderful200.menudialog.SlideDeleteMenu10

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.TextView
import com.ecs.androidwonderful200.R

/**
 * 作者：RedKeyset on 2019/7/31 17:18
 * 邮箱：redkeyset@aliyun.com
 * 滑动按钮自定义控件
 */
class SlidingButtonView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : HorizontalScrollView(context, attrs, defStyleAttr) {

    private var lTextView_Delete: TextView? = null      //删除按钮
    private var lScrollWidth: Int = 0               //横向滑动的范围
    private var first: Boolean? = false            //标记第一次进入获取删除按钮控件

    init {

        this.overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //第一次进入获取删除按钮控件
        if ((!first!!)!!) {
            lTextView_Delete = findViewById(R.id.tv_delete) as TextView
            first = true
        }

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        //默认隐藏删除按钮
        if (changed) {
            this.scrollTo(0, 0)
            //获取水平滚动条可以滑动的范围，即右侧按钮的宽度
            lScrollWidth = lTextView_Delete!!.width
        }

    }

    /**
     * 滑动手指抬起时的手势判断
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                changeScrollx()            //根据滑动距离判断是否显示删除按钮
                return true
            }
            else -> {
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * 根据滑动距离判断是否显示删除按钮
     */
    fun changeScrollx() {
        //触摸滑动的距离大于删除按钮宽度的一半
        if (scrollX >= lScrollWidth / 2) {
            //显示删除按钮
            this.smoothScrollTo(lScrollWidth, 0)
        } else {
            //隐藏删除按钮
            this.smoothScrollTo(0, 0)
        }
    }

}