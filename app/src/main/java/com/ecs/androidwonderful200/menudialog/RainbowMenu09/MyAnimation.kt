package com.ecs.androidwonderful200.menudialog.RainbowMenu09

import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.RelativeLayout
/**
 * Created by Administrator on 2017/2/6.
 */

object MyAnimation {
    //菜单进入动画
    fun animationIN(viewGroup: RelativeLayout?, duration: Int) {
        for (i in 0 until viewGroup!!.childCount) {        //便利布局中按钮控件
            viewGroup.getChildAt(i).visibility = View.VISIBLE    //设置显示
            viewGroup.getChildAt(i).isFocusable = true             //获得焦点
            viewGroup.getChildAt(i).isClickable = true             //可以点击
        }

        val animation: Animation                                         //动画对象
        /**
         * 旋转动画
         * RotateAnimation(fromDegrees, toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue)
         * fromDegrees 开始旋转角度
         * toDegrees 旋转到的角度
         * pivotXType X轴 参照物
         * pivotXValue x轴 旋转的参考点
         * pivotYType Y轴 参照物
         * pivotYValue Y轴 旋转的参考点
         */
        animation = RotateAnimation(-180f, 0f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 1.0f)        //创建旋转动画
        animation.setFillAfter(true)                            //停留在动画结束位置
        animation.setDuration(duration.toLong())                         //动画过程的时间
        viewGroup.startAnimation(animation)                     //启动动画

    }

    //菜单退出动画
    fun animationOUT(viewGroup: RelativeLayout?, duration: Int, startOffSet: Int) {
        val animation: Animation                                      //动画对象
        animation = RotateAnimation(0f, -180f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1.0f)              //创建旋转动画
        animation.setFillAfter(true)                            //停留在动画结束位置
        animation.setDuration(duration.toLong())                         //动画过程的时间
        animation.setStartOffset(startOffSet.toLong())
        //设置动画监听事件
        animation.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {
                // TODO Auto-generated method stub

            }

            override fun onAnimationRepeat(animation: Animation) {
                // TODO Auto-generated method stub

            }

            /**
             * 动画结束后的事件处理
             */
            override fun onAnimationEnd(animation: Animation) {
                for (i in 0 until viewGroup!!.childCount) {
                    viewGroup.getChildAt(i).visibility = View.GONE       //设置隐藏菜单中按钮
                    viewGroup.getChildAt(i).isFocusable = false            //失去焦点
                    viewGroup.getChildAt(i).isClickable = false            //不可单击
                }

            }
        })

        viewGroup!!.startAnimation(animation)                                //启动退出动画
    }
}