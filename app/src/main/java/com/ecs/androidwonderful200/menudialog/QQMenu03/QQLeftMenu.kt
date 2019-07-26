package com.ecs.androidwonderful200.menudialog.QQMenu03

import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.HorizontalScrollView
import android.widget.LinearLayout

/**
 *手机QQ 侧滑菜单
 */
class QQLeftMenu(context: Context, attrs: AttributeSet) : HorizontalScrollView(context, attrs) {
    private var mScrollView: LinearLayout? = null            //定义横向滚动条布局
    private var mMenu: ViewGroup? = null                 //定义菜单区域
    private var mContent: ViewGroup? = null             //定义主显示区域
    private val mScreenWidth: Int              //定义屏幕宽度
    private var mMenuRightPadding = 50   //定义菜单右边距为50dp
    private var call: Boolean = false                   //定义只设置一次自己和子视图的宽和高
    private var mMenuWidth: Int = 0                 //定义菜单宽度

    init {   //构造方法
        //获取窗口管理器服务
        val wm = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        //创建显示尺寸对象
        val outMetrics = DisplayMetrics()
        //获取当前屏幕的宽高尺寸
        wm.defaultDisplay.getMetrics(outMetrics)
        //为屏幕宽度赋值
        mScreenWidth = outMetrics.widthPixels
        //将50dp边距转为像素值px
        mMenuRightPadding = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 50f,
                context.resources.displayMetrics).toInt()
        //隐藏默认显示的横向滚动条
        this.isHorizontalScrollBarEnabled = false

    }

    //设置滚动视图与子视图的宽和高
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (!call) {                                          //用于判断只设置一次尺寸
            mScrollView = getChildAt(0) as LinearLayout          //获取滚动视图中的子布局
            mMenu = mScrollView!!.getChildAt(0) as ViewGroup      //获取菜单区域
            mContent = mScrollView!!.getChildAt(1) as ViewGroup   //获取主显示区域
            //设置菜单宽度
            mMenu!!.layoutParams.width = mScreenWidth - mMenuRightPadding
            mMenuWidth = mMenu!!.layoutParams.width
            //设置主显示区域宽度
            mContent!!.layoutParams.width = mScreenWidth
            call = true
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    //设置偏移量让菜单隐藏
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        if (changed) {
            this.scrollTo(mMenuWidth, 0)    //滚动条向右移动，主显示区域向左移动
        }
    }

    //判断手指抬起时隐藏菜单还是显示菜单
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        val action = ev.action
        when (action) {
            MotionEvent.ACTION_UP -> {
                val scrollX = scrollX                 //隐藏左边的宽度
                if (scrollX >= mMenuWidth / 2) {             //如果隐藏左边的宽度大于菜单宽度的1/2时
                    this.smoothScrollTo(mMenuWidth, 0)        //隐藏菜单
                } else {
                    this.smoothScrollTo(0, 0)              //显示菜单
                }
                return true
            }
        }
        return super.onTouchEvent(ev)
    }

}
