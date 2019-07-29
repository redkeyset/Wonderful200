package com.ecs.androidwonderful200.menudialog.MessageNotifcation06

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ecs.androidwonderful200.R
import org.jetbrains.anko.toast
/**
 * Notifcation 的使用
 * */
class MessageNotifcation06Activity : AppCompatActivity() {

    var notificationManager: NotificationManager? = null
    var notification: NotificationCompat.Builder? = null
    val CHANNELID = "NotifcationA"
    val CHANNELNAME = "默认通知"
    val NOTIFYID = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_notifcation06)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notification = NotificationCompat.Builder(this, CHANNELID)

        notification!!.setAutoCancel(true)
        notification!!.setTicker("这是一个通知！")
        notification!!.setSmallIcon(R.drawable.satel_img1)
        notification!!.setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.satel_img3))
        notification!!.setContentTitle("通知标题")
        notification!!.setContentText("点击查看详情！")
        notification!!.setWhen(System.currentTimeMillis())

        // Android8.0及其以上版本，不加此句代码，通知不会弹出！
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager!!.createNotificationChannel(NotificationChannel(CHANNELID,CHANNELNAME,NotificationManager.IMPORTANCE_HIGH))
        }
    }

    open fun onSendNotifcation(v: View) {
        toast("发送一个通知！")
        var intent = Intent()
        intent.setClass(this,MessageActivity().javaClass)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        notification!!.setContentIntent(pi)
        notificationManager!!.notify(NOTIFYID, notification!!.build())
    }
}
