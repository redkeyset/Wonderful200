package com.ecs.androidwonderful200.menudialog.QQDialog07

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.ecs.androidwonderful200.R

/**
 * 作者：RedKeyset on 2019/7/30 10:42
 * 邮箱：redkeyset@aliyun.com
 * 自定义构造方法
 */
class Mdialog(context: Context) : Dialog(context, R.style.mdialog) {
    private val button_cancel: Button
    private val button_exit: Button        //定义取消与确认按钮
    private val tv: TextView                                   //定义标题文字

    init {
        val view = LayoutInflater.from(getContext()).inflate(R.layout.mdialoglayout, null)  //通过LayoutInflater获取布局
        tv = view.findViewById(R.id.title)   //获取显示标题的文本框控件
        button_cancel = view.findViewById(R.id.btn_cancel) as Button    //获取取消按钮
        button_exit = view.findViewById(R.id.btn_exit) as Button  //获取确认退出按钮
        setContentView(view)  //设置显示的视图
    }

    //设置显示的标题文字
    fun setTv(content: String) {
        tv.text = content
    }

    //取消按钮监听
    fun setOnCancelListener(listener: View.OnClickListener) {
        button_cancel.setOnClickListener(listener)
    }

    //退出按钮监听
    fun setOnExitListener(listener: View.OnClickListener) {
        button_exit.setOnClickListener(listener)
    }
}