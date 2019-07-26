package com.ecs.androidwonderful200.menudialog.AnimDialog05

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.KeyEvent
import android.widget.Button
import com.ecs.androidwonderful200.R

/**
 * 仿58同城 退出动画
 */
class AnimDialog05Activity : AppCompatActivity() {
    private var button_determine: Button? = null
    private var button_cancel: Button? = null      //定义对话框按钮
    private var dlg: AlertDialog? = null                               //定义对话框

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_dialog05)
    }

    /**
     * 判断当单击手机返回按钮时，从手机顶部向下移动对话
     * 再次单击返回按钮,对话框将从中间向底部移动消失对话框
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        //判断如果单击了返回按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //创建对话框实例
            dlg = AlertDialog.Builder(this).create()
            dlg!!.show()                             //显示对话框
            val window = dlg!!.window        //获取对话框窗口
            window!!.setGravity(Gravity.CENTER)     //此处设置dialog显示在中心位置
            window.setWindowAnimations(R.style.mystyle)      //添加动画
            window.setContentView(R.layout.dialog_layout)   //设置对话框布局文件
            //获取对话框确定按钮
            button_determine = window.findViewById(R.id.btn_determine) as Button
            //获取对话框取消按钮
            button_cancel = window.findViewById(R.id.btn_cancel) as Button
            initEvent()                                       //调用初始化事件方法
        }
        return super.onKeyDown(keyCode, event)
    }

    /**
     * 该方法出事对话框中按钮的事件，单击确定按钮退出该应用
     * 单击取消按钮，对话框将移动至底部消失
     */
    private fun initEvent() {
        button_determine!!.setOnClickListener {
            dlg!!.dismiss()          //对话框移动到底部消失
            finish()               //关闭当前应用
        }
        button_cancel!!.setOnClickListener {
            dlg!!.dismiss()          //对话框移动到底部消失
        }
    }
}
