package com.ecs.androidwonderful200.menudialog.RainbowMenu09

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ecs.androidwonderful200.R
import android.widget.RelativeLayout
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_rainbow_menu09.*
import org.jetbrains.anko.toast

/**
 * 旋转彩虹Menu
 */
class RainbowMenu09Activity : AppCompatActivity(), View.OnClickListener {
    //二级与三级菜单布局
    private var l2: RelativeLayout? = null
    private var l3: RelativeLayout? = null

    private var isl2Show = true  //判断二级菜单是否显示
    private var isl3Show = true  //盘算三级菜单是否显示

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rainbow_menu09)

        //获取二级菜单与三级菜单布局
        l2 = findViewById<View>(R.id.level_2) as RelativeLayout?
        l3 = findViewById<View>(R.id.level_3) as RelativeLayout?

        initView()
    }

    private fun initView() {
        val childCount = activity_main.childCount
        for (item in 0..(childCount - 1)) {
            val childAt = activity_main.getChildAt(item)
            if (childAt is ImageButton) { // 先判断 View 的子是不是ImageButton，如果是ImageButton 就设置点击
                childAt.setOnClickListener(this)
            } else if (childAt is RelativeLayout) {   //如果是 RelativeLayout，就进行再次遍历
                for (item1 in 0..(childAt.childCount - 1)) {
                    val childAt1 = childAt.getChildAt(item1)
                    if (childAt1 is ImageButton) {
                        childAt1.setOnClickListener(this)
                    }
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            b_1 -> {toast("b_1")}
            b_3 -> {toast("b_3")}
            c1 -> {toast("c1")}
            c2 -> {toast("c2")}
            c3 -> {toast("c3")}
            c4 -> {toast("c4")}
            c5 -> {toast("c5")}
            c6 -> {toast("c6")}
            c7 -> {toast("c7")}

            a_1 -> {
                show1and2()
            }

            b_2 -> {
                show3()
            }
        }
    }

    //单击该按钮显示或隐藏三级菜单
    private fun show3() {
        if (isl3Show) {
            //隐藏3级导航菜单
            MyAnimation.animationOUT(l3, 500, 0)
        } else {
            //显示3级导航菜单
            MyAnimation.animationIN(l3, 500)

        }
        //根据当前的显示状态设置为相反的状态，如3级菜单已经关闭。这里将设置状态为false
        isl3Show = !isl3Show
    }

    //单击该按钮显示二级菜单或隐藏二级三级菜单
    private fun show1and2() {
        if (!isl2Show) {
            //显示2级导航菜单
            MyAnimation.animationIN(l2, 500)
        } else {
            if (isl3Show) {
                //隐藏3级导航菜单
                MyAnimation.animationOUT(l3, 500, 0)
                //隐藏2级导航菜单
                MyAnimation.animationOUT(l2, 500, 500)
                isl3Show = !isl3Show
            } else {
                //隐藏2级导航菜单
                MyAnimation.animationOUT(l2, 500, 0)
            }
        }
        isl2Show = !isl2Show
    }
}
