package com.ecs.androidwonderful200.menudialog.CircleMenu08

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.ecs.androidwonderful200.R
import org.jetbrains.anko.toast

class CircleMenu08Activity : AppCompatActivity() {
    private var mCircleMenuLayout: CircleMenuLayout? = null//自定义圆盘菜单
    private val mItemTexts = arrayOf("放大镜 ", "尺子", "分贝测试仪", "手电筒", "计算器", "SOS")//圆盘菜单显示文字
    private val mItemImgs = intArrayOf(R.drawable.home_mbank_1_normal, R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal, R.drawable.home_mbank_4_normal, R.drawable.home_mbank_5_normal, R.drawable.home_mbank_6_normal)//圆盘菜单显示图片

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_menu08)//导入布局

        //初始化圆盘控件
        mCircleMenuLayout = findViewById(R.id.id_menulayout) as CircleMenuLayout
        //初始化圆盘控件菜单
        mCircleMenuLayout!!.setMenuItemIconsAndTexts(mItemImgs, mItemTexts)

        mCircleMenuLayout!!.setOnMenuItemClickListener(object : CircleMenuLayout.OnMenuItemClickListener {
            override fun itemClick(view: View, pos: Int) {
                toast("点击了${mItemTexts.get(pos)}！")
            }

            override fun itemCenterClick(view: View) {
                toast("点击了中心位置！")
            }
        })

    }
}
