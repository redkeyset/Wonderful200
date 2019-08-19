package com.ecs.wonderful200.sharedpreferences024

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_shared_prefer.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class SharedPreferActivity : AppCompatActivity() {
    lateinit var username: String
    lateinit var password: String
    lateinit var mySharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_prefer)
    }

    fun onBack() {
        toast("返回")
        finish()
    }

    fun onRegister() {
        toast("注册")
        startActivity(intentFor<TestActivity>())
    }

    fun onFindPwd() {
        toast("忘记密码")
    }

    fun onLogin() {
        username = et_username.text.toString().trim()
        password = et_password.text.toString().trim()

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            toast("用户名或密码不可为空")
        } else {
            //实例化SharedPreferences对象
            mySharedPreferences = getSharedPreferences("myuserinfo", Context.MODE_PRIVATE)
            //实例化SharedPreferences.Editor对象（第二步）
            val editor = mySharedPreferences.edit()
            //用putString的方法保存数据
            editor.putString("username", username)
            editor.putString("pwd", password)
            //提交当前数据
            editor.commit()
            toast("信息保存成功！")
        }
    }

    fun onShow() {
        //实例化SharedPreferences对象
        mySharedPreferences = getSharedPreferences("myuserinfo", Context.MODE_PRIVATE)
        // 使用getString方法获得value，注意第2个参数是value的默认值
        var username = mySharedPreferences.getString("username", "没有保存的数据")
        var pwd = mySharedPreferences.getString("pwd", "没有保存的数据")

        AlertDialog.Builder(this)
                .setTitle("显示保存的用户信息")
                .setMessage("用户名：$username \n 密码：$pwd")
                .setCancelable(false)
                .setPositiveButton("知道了") { dialog, _ ->
                    dialog.cancel()
                }
                .show()
    }
}
