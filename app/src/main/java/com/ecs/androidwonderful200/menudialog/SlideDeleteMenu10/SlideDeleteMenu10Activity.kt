package com.ecs.androidwonderful200.menudialog.SlideDeleteMenu10

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.ecs.androidwonderful200.R
import kotlinx.android.synthetic.main.activity_slide_delete_menu10.*

class SlideDeleteMenu10Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_delete_menu10)
        initView()             //初始化控件
        setAdapter()           //设置适配器
    }

    private fun setAdapter() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = SlideAdapter(this)
        recyclerview.itemAnimator = DefaultItemAnimator()
    }

    private fun initView() {

    }
}
