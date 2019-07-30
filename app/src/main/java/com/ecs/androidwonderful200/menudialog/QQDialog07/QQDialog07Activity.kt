package com.ecs.androidwonderful200.menudialog.QQDialog07

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.ecs.androidwonderful200.R

class QQDialog07Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qqdialog07)
        //不显示系统的标题栏,
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

    }

    /**
     * 设置退出账号按钮弹出对话框
     *
     * @param view
     */
    fun OnExitNumber(view: View) {
        val mdialog = Mdialog(this)      //实例化自定义对话框
        //对话框中退出按钮事件
        mdialog.setOnExitListener(View.OnClickListener {
            //如果对话框处于显示状态
            if (mdialog.isShowing) {
                mdialog.dismiss()                     //关闭对话框
                finish()                              //关闭当前界面
            }
        })
        //对话框中取消按钮事件
        mdialog.setOnCancelListener(View.OnClickListener {
            if (mdialog != null && mdialog.isShowing) {
                mdialog.dismiss()                  //关闭对话框
            }
        })
        mdialog.show()

    }
}
