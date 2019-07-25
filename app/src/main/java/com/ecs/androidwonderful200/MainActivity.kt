package com.ecs.androidwonderful200

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ecs.androidwonderful200.menudialog.AnnouncementDrawer.AnnouncementDrawer02Activity
import com.ecs.androidwonderful200.menudialog.weimenu01.WeiMenu01Activity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        btnWeiMenu.setOnClickListener(this)
        btnAnnouncementDrawer.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            btnWeiMenu -> startActivity(intentFor<WeiMenu01Activity>())
            btnAnnouncementDrawer -> startActivity(intentFor<AnnouncementDrawer02Activity>())
        }
    }
}
