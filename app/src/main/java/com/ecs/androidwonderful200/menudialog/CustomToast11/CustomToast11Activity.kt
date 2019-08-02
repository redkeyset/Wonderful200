package com.ecs.androidwonderful200.menudialog.CustomToast11

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.ecs.androidwonderful200.R
import kotlinx.android.synthetic.main.activity_custom_toast11.*

class CustomToast11Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_toast11)

        btnTishi.setOnClickListener {
            val myToast = MyToast.Builder(this)
                    .setMessage("自定义Toast效果！")//设置提示文字
                    .setBackgroundColor(0xe9ff4587.toInt())//设置背景颜色
                    .setGravity(Gravity.CENTER)//设置吐司位置
                    .showIcon(true)//是否显示图标
                    .build()//创建吐司
                    .show()
        }
    }
}
