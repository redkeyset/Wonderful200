package com.ecs.androidwonderful200.menudialog.weimenu01

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.widget.PopupWindow
import android.widget.Toast
import com.ecs.androidwonderful200.R
import kotlinx.android.synthetic.main.menu.view.*

class WeiMenu01Activity : AppCompatActivity(), View.OnClickListener {
    var popupWindow: PopupWindow? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wei_menu01)
    }

    fun OnMenu(view: View) {
        // 获取自定义的菜单布局文件
        val popupWindow_view = layoutInflater.inflate(R.layout.menu, null, false)
        // 初始化 popupWindow_view （R.layout.menu）包含的view
        initItemView(popupWindow_view)
        // 创建PopupWindow实例,设置菜单宽度和高度为包裹其自身内容
        popupWindow = PopupWindow(popupWindow_view, ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, true)
        //设置菜单显示在按钮的下面
        popupWindow!!.showAsDropDown(findViewById<View>(R.id.btn_menu), 0, 0)
        // 点击其他地方消失
        popupWindow_view.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                //如果菜单存在并且为显示状态，就关闭菜单并初始化菜单
                setPopDismiss()
                return false
            }
        })
    }

    private fun initItemView(v: View) {
//        单个点击
//        popupWindow_view.findViewById<Button>(R.id.btnGrop).setOnClickListener {
//            Toast.makeText(this, "点击了btnGrop", Toast.LENGTH_SHORT).show()
//        }
        v.btnGrop.setOnClickListener(this)
        v.btnAdd.setOnClickListener(this)
        v.btnScan.setOnClickListener(this)
        v.btnCard.setOnClickListener(this)
        v.btnHelp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            v?.btnGrop ->
                Toast.makeText(this, "点击了btnAdd", Toast.LENGTH_SHORT).show()
            v?.btnAdd ->
                Toast.makeText(this, "点击了btnAdd", Toast.LENGTH_SHORT).show()
            v?.btnScan ->
                Toast.makeText(this, "点击了btnScan", Toast.LENGTH_SHORT).show()
            v?.btnCard ->
                Toast.makeText(this, "点击了btnCard", Toast.LENGTH_SHORT).show()
            v?.btnHelp ->
                Toast.makeText(this, "点击了btnHelp", Toast.LENGTH_SHORT).show()
        }
//        点击后PopupWindow Dismiss
        setPopDismiss()
    }

    private fun setPopDismiss() {
        if (popupWindow != null && popupWindow!!.isShowing) {
            popupWindow!!.dismiss()
            popupWindow = null
        }
    }
}