package com.ecs.androidwonderful200

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ecs.androidwonderful200.menudialog.AnimDialog05.AnimDialog05Activity
import com.ecs.androidwonderful200.menudialog.AnnouncementDrawer02.AnnouncementDrawer02Activity
import com.ecs.androidwonderful200.menudialog.MessageNotifcation06.MessageNotifcation06Activity
import com.ecs.androidwonderful200.menudialog.QQMenu03.QQMenu03Activity
import com.ecs.androidwonderful200.menudialog.SatellistMenu04.SatellistMenu04Activity
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
        btnQQMenu.setOnClickListener(this)
        btnSatelListMenu.setOnClickListener(this)
        btnAnimDialog.setOnClickListener(this)
        btnMessageNotifcation.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            btnWeiMenu -> startActivity(intentFor<WeiMenu01Activity>())
            btnAnnouncementDrawer -> startActivity(intentFor<AnnouncementDrawer02Activity>())
            btnQQMenu -> startActivity(intentFor<QQMenu03Activity>())
            btnSatelListMenu -> startActivity(intentFor<SatellistMenu04Activity>())
            btnAnimDialog -> startActivity(intentFor<AnimDialog05Activity>())
            btnMessageNotifcation -> startActivity(intentFor<MessageNotifcation06Activity>())
        }
    }
}
