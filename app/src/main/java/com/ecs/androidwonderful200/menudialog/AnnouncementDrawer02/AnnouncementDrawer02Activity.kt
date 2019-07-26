package com.ecs.androidwonderful200.menudialog.AnnouncementDrawer02

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SlidingDrawer
import android.widget.Toast
import com.ecs.androidwonderful200.R
import kotlinx.android.synthetic.main.activity_announcement_drawer02.*

/**
 * https://blog.csdn.net/SJF0115/article/details/7348532
 * https://www.jb51.net/article/42123.htm
 * SlidingDrawer配置上采用了水平展开或垂直展开两种（android:orientation）方式，在XML里必须指定其使用的android:handle与android:content
 * 1、android:handle="@id/layout1"  相当于裸露出来的抽屉把手
 * 2、android:content="@id/myView"  相当于抽屉箱体本身
 * 3、android:orientation="horizontal"  决定抽屉的方向
 * */
class AnnouncementDrawer02Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_announcement_drawer02)

        drawerView.setOnDrawerOpenListener {
            Toast.makeText(this, "OnDrawerOpen", Toast.LENGTH_SHORT).show()
        }

        drawerView.setOnDrawerScrollListener(object : SlidingDrawer.OnDrawerScrollListener {
            override fun onScrollStarted() {
                Toast.makeText(drawerView.context, "onScrollStarted", Toast.LENGTH_SHORT).show()
            }
            override fun onScrollEnded() {
                Toast.makeText(drawerView.context, "onScrollEnded", Toast.LENGTH_SHORT).show()
            }
        })

        drawerView.setOnDrawerCloseListener {
            Toast.makeText(this, "OnDrawerClose", Toast.LENGTH_SHORT).show()
        }

//        drawerView.close()
//        drawerView.open()
//        drawerView.isMoving
//        drawerView.isOpened

    }
}
