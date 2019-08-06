package com.ecs.androidwonderful200.menudialog.DownMenu12

import android.content.res.Resources
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.TextView
import com.ecs.androidwonderful200.R
import kotlinx.android.synthetic.main.activity_down_menu12.*
import org.jetbrains.anko.toast
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build


/**
 *
 */
class DownMenu12Activity : AppCompatActivity(), View.OnClickListener {
    var index: Int = 0
    var oldIndex: Int = 0
    var popupWindow: PopupWindow? = null
    var isShow: Boolean = false
    var popText: TextView? = null

    override fun onClick(v: View?) {
        var str: String = ""
        when (v) {
            select_1 -> {
                str = "全部分类"
                index = 1
            }
            select_2 -> {
                str = "类型"
                index = 2
            }
            select_3 -> {
                str = "热门"
                index = 3
            }
            select_4 -> {
                str = "难易"
                index = 4
            }
        }

        if (null != popupWindow) {
            popupWindow!!.dismiss()
        }

        setMenu(str)
    }

    private fun setMenu(message: String) {
        val measuredHeight = rl_content.measuredHeight  // 获取高度1
        val measuredHeightAndState = rl_content.measuredHeightAndState // 获取高度2
        toast("measuredHeight:$measuredHeight ,measuredHeightAndState:$measuredHeightAndState")

        if (null == popupWindow) {
            val view = layoutInflater.inflate(R.layout.pop_layout, null)
            popText = view.findViewById<TextView>(R.id.text)
            popupWindow = PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, measuredHeight)
            popupWindow!!.animationStyle = R.style.PopupWindowAnimation //设置动画效果
            popupWindow!!.isOutsideTouchable = false //设置点击弹窗外部不关闭弹窗
            popupWindow!!.isFocusable = false //设置不获取焦点


            val drawable1 = this.resources.getDrawable(R.drawable.bg) // 获取Drawable 方法一

            // 获取Drawable 方法二
            val drawable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.resources.getDrawable(R.drawable.bg, this.theme)
            } else {
                TODO("VERSION.SDK_INT < LOLLIPOP")
            }

//            drawable.setColorFilter(Color.parseColor("#ff4400"),PorterDuff.Mode.DARKEN) //使 drawable 发生偏色

            popupWindow!!.setBackgroundDrawable(drawable1)

//            popupWindow!!.isTouchable = true
        }

        if (isShow) {
            if (index == oldIndex) {
                popupWindow!!.dismiss()
                isShow = false
                return
            }
        }

        popText!!.text = message
        popupWindow!!.showAsDropDown(tag_line, 0, 0)
        isShow = true
        oldIndex = index
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_down_menu12)
        initView()
    }

    private fun initView() {
        select_1.setOnClickListener(this)
        select_2.setOnClickListener(this)
        select_3.setOnClickListener(this)
        select_4.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        popupWindow!!.dismiss()
    }
}
