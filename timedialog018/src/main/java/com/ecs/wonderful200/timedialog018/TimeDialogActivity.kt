package com.ecs.wonderful200.timedialog018

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class TimeDialogActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {

    }
    private val ONE = 1
    private val TWO = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_dialog)
        //初始化点击事件
        val timeBtnListener = BtnOnClickListener(ONE)
        val timeBtnListener1 = BtnOnClickListener(TWO)
        //初始化控件
        val btnTime = findViewById<View>(R.id.btnTime) as Button
        val btnTime1 = findViewById<View>(R.id.btnTime1) as Button
        //绑定点击事件
        btnTime.setOnClickListener(timeBtnListener)
        btnTime1.setOnClickListener(timeBtnListener1)
        //显示当前日期控件
        val text = findViewById<View>(R.id.text) as TextView
        //设置文本格式
        val formatter = SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss")
        //获取当前系统时间
        val curDate = Date(System.currentTimeMillis())//获取当前时间
        //转换时间为设置格式
        val str = formatter.format(curDate)
        //设置显示文字
        text.text = str
    }

    override fun onCreateDialog(id: Int): Dialog? {
        //用来获取日期和时间的
        val calendar = Calendar.getInstance()
        var dialog: Dialog? = null
        when (id) {
            ONE -> {
                //时间选择弹窗回调函数
                val timeListener = TimePickerDialog.OnTimeSetListener { timerPicker, hourOfDay, minute ->
                    //初始化显示设置时间控件
                    val textV = findViewById<View>(R.id.textV) as TextView
                    //小时
                    val hourOfDays: String
                    //分
                    val minutes: String
                    if (minute < 10) {
                        //为了美观 小于10补0
                        minutes = "0$minute"
                    } else {
                        minutes = minute.toString() + ""
                    }
                    if (hourOfDay < 10) {
                        //为了美观 小于10补0
                        hourOfDays = "0$hourOfDay"
                    } else {
                        hourOfDays = "" + hourOfDay
                    }
                    //设置控件显示选择时间
                    textV.text = hourOfDays + ":" +
                            minutes
                }
                //时间选择弹窗
                dialog = TimePickerDialog(this, timeListener,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        //是否为二十四制
                        false)
            }
            TWO -> {
                //时间选择弹窗
                val timeListener1 = TimePickerDialog.OnTimeSetListener { timerPicker, hourOfDay, minute ->
                    //初始化显示设置时间控件
                    val textV1 = findViewById<View>(R.id.textV1) as TextView
                    //小时
                    val hourOfDays: String
                    //分
                    val minutes: String
                    if (minute < 10) {
                        //为了美观 小于10补0
                        minutes = "0$minute"
                    } else {
                        minutes = minute.toString() + ""
                    }
                    if (hourOfDay < 10) {
                        //为了美观 小于10补0
                        hourOfDays = "0$hourOfDay"
                    } else {
                        hourOfDays = "" + hourOfDay
                    }
                    //设置控件显示选择时间
                    textV1.text = hourOfDays + ":" +
                            minutes
                }
                //是否为二十四制
                dialog = TimePickerDialog(this, timeListener1,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true)
            }
            else -> {
            }
        }
        return dialog
    }

    // 成员内部类,此处为提高可重用性，也可以换成匿名内部类
    private inner class BtnOnClickListener(dialogId: Int) : View.OnClickListener {
        //默认为0则不显示对话框
        private var dialogId = 0

        init {
            this.dialogId = dialogId
        }

        override fun onClick(view: View) {
            showDialog(dialogId)
        }
    }

}
