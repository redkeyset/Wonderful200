package com.ecs.androidwonderful200.menudialog.SlideDeleteMenu10

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager


object Utils {
    /**
     * 获得屏幕宽度
     */
   open fun getScreenWidth(context: Context): Int {
        //获取窗口管理服务
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        //屏幕参数对象
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

}
