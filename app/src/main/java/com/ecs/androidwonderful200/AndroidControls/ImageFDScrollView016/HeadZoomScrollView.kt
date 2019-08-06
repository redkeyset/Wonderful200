package com.ecs.androidwonderful200.AndroidControls.ImageFDScrollView016

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView


/**
 * 作者：RedKeyset on 2019/8/6 17:03
 * 邮箱：redkeyset@aliyun.com
 */

class HeadZoomScrollView : ScrollView {
    //    用于记录下拉位置
    var y1 = 0f
//    private var y = 0f
    //    zoomView原本的宽高
    private var zoomViewWidth = 0
    private var zoomViewHeight = 0
    //    是否正在放大
    private var mScaling = false
    //    放大的view，默认为第一个子view
    private var zoomView: View? = null
    //    滑动放大系数，系数越大，滑动时放大程度越大
    private var mScaleRatio = 1f
    //    最大的放大倍数
    private var mScaleTimes = 2f
    //    回弹时间系数，系数越小，回弹越快
    private var mReplyRatio = 0.5f
    private var onScrollListener: OnScrollListener? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    fun setZoomView(zoomView: View) {
        this.zoomView = zoomView
    }

    fun setmScaleRatio(mScaleRatio: Float) {
        this.mScaleRatio = mScaleRatio
    }

    fun setmScaleTimes(mScaleTimes: Int) {
        this.mScaleTimes = mScaleTimes.toFloat()
    }

    fun setmReplyRatio(mReplyRatio: Float) {
        this.mReplyRatio = mReplyRatio
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        //        屏蔽上下越界动画
        overScrollMode = View.OVER_SCROLL_NEVER
        //        获得默认第一个view
        if (getChildAt(0) != null && getChildAt(0) is ViewGroup && zoomView == null) {
            val vg = getChildAt(0) as ViewGroup
            if (vg.childCount > 0) {
                zoomView = vg.getChildAt(0)
            }
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (zoomViewWidth <= 0 || zoomViewHeight <= 0) {
            zoomViewWidth = zoomView!!.getMeasuredWidth()
            zoomViewHeight = zoomView!!.getMeasuredHeight()
        }
        if (zoomView == null || zoomViewWidth <= 0 || zoomViewHeight <= 0) {
            return super.onTouchEvent(ev)
        }
        when (ev.action) {
            MotionEvent.ACTION_MOVE -> {
                if (!mScaling) {
                    if (scrollY == 0) {
                        y1 = ev.y
//                        y = ev.y//滑动到顶部时，记录位置
                    } else {

                    }
                }
                val distance = ((ev.y - y) * mScaleRatio).toInt()
                if (distance < 0)//若往下滑动
                mScaling = true
                setZoom(distance.toFloat())
                return true
            }
            MotionEvent.ACTION_UP -> {
                mScaling = false
                replyView()
            }
        }
        return super.onTouchEvent(ev)
    }

    //放大view
    private fun setZoom(s: Float) {
        val scaleTimes = ((zoomViewWidth + s) / (zoomViewWidth * 1.0)).toFloat()
        //如超过最大放大倍数，直接返回
        if (scaleTimes > mScaleTimes) return
        val layoutParams = zoomView!!.getLayoutParams()
        layoutParams.width = (zoomViewWidth + s).toInt()
        layoutParams.height = (zoomViewHeight * ((zoomViewWidth + s) / zoomViewWidth)).toInt()
        //设置控件水平居中
        (layoutParams as MarginLayoutParams).setMargins(-(layoutParams.width - zoomViewWidth) / 2, 0, 0, 0)
        zoomView!!.setLayoutParams(layoutParams)
    }

    /**回弹 */
    private fun replyView() {
        val distance = zoomView!!.getMeasuredWidth() - zoomViewWidth
        // 设置动画
        val anim = ObjectAnimator.ofFloat(distance, "",0.0f).setDuration((distance * mReplyRatio).toLong())
        anim.addUpdateListener { animation -> setZoom(animation.animatedValue as Float) }
        anim.start()
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        if (onScrollListener != null) onScrollListener!!.onScroll(l, t, oldl, oldt)
    }

    fun setOnScrollListener(onScrollListener: OnScrollListener) {
        this.onScrollListener = onScrollListener
    }

    /**滑动监听 */
    interface OnScrollListener {
        fun onScroll(scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int)
    }


}