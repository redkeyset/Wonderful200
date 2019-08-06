package com.ecs.androidwonderful200.menudialog.CustomToast11

import android.content.Context
import android.graphics.Bitmap
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.ecs.androidwonderful200.R

/**
 * 作者：RedKeyset on 2019/8/2 10:43
 * 邮箱：redkeyset@aliyun.com
 */

class MyToast(context: Context) {
    val toast: Toast//Toast对象
    val view: View//Toast的UI效果
    val icon: ImageView//图标
    val message: TextView//内容

    init {
        toast = Toast(context)
        view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null)
        icon = view.findViewById(R.id.toast_icon) as ImageView
        message = view.findViewById(R.id.toast_message)
    }

    /**
     * 显示
     */
    fun show() {
        this.toast.show()
    }

    class Builder(private val mContext: Context//上下文
    ) {
        private var icon: Bitmap? = null//图标图片
        private var iconID = R.mipmap.ic_launcher//图标资源ID
        private var message: String? = null//内容
        private var backgroundColor = 0x56000000//背景颜色
        private var duration = Toast.LENGTH_SHORT//设置时间
        private var mine: MyToast? = null
        private var gravity = Gravity.NO_GRAVITY//设置位置
        private var offsetX = 0//设置偏移度X
        private var offsetY = 0//设置偏移度Y
        private var isShowIcon: Boolean = false//是否显示图标
        /**
         * 设置ICON
         * @param bitmap
         * @return
         */
        fun setIcon(bitmap: Bitmap): Builder {
            this.icon = bitmap
            return this
        }

        fun setIcon(@DrawableRes resId: Int): Builder {
            this.iconID = resId
            return this
        }

        fun showIcon(showIcon: Boolean): Builder {
            this.isShowIcon = showIcon
            return this
        }

        /**
         * 设置内容
         */
        fun setMessage(hintMessage: String): Builder {
            this.message = hintMessage
            return this
        }

        /**
         * 设置吐司时长
         */
        fun setDuration(type: Int): Builder {
            this.duration = type
            return this
        }

        /**
         * 设置背景
         */
        fun setBackgroundColor(@ColorInt color: Int): Builder {
            this.backgroundColor = color
            return this
        }

        /**
         * 设置位置
         */
        fun setGravity(gravity: Int): Builder {
            this.gravity = gravity
            return this
        }

        /**
         * 偏移量
         */
        fun setOffsetX(x: Int): Builder {
            this.offsetX = x
            return this
        }

        fun setOffsetY(y: Int): Builder {
            this.offsetY = y
            return this
        }

        /**
         * 创建MyToast对象
         */
        fun build(): MyToast {
            if (null == mine) {
                mine = MyToast(mContext)//创建对象
            }
            if (isShowIcon) {
                //隐藏图标
                mine!!.icon.setVisibility(View.VISIBLE)
                if (null != icon) {//判断是否显示图标
                    mine!!.icon.setImageBitmap(icon)//设置图片
                } else {
                    //设置图片
                    mine!!.icon.setBackgroundResource(iconID)
                }
            }
            if (!message!!.isEmpty()) {//判断内容是否为空
                mine!!.message.text = message
            } else {
                mine!!.message.text = ""
            }
            //设置背景
            mine!!.view.setBackground(BackgroundDrawable(backgroundColor, mContext))
            mine!!.toast.duration = duration//设置时长
            mine!!.toast.view = mine!!.view//添加自定义效果
            mine!!.toast.setGravity(gravity, offsetX, offsetY)//设置偏移量
            return mine as MyToast
        }
    }

}