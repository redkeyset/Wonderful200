package com.ecs.androidwonderful200.AndroidControls.ZFPlay014

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ecs.androidwonderful200.R
import kotlinx.android.synthetic.main.activity_zfplay014.*
import org.jetbrains.anko.toast


class ZFPlay014Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zfplay014)

        pwdView.setOnFinishInput(object : OnPasswordInputFinish {
            override fun inputFinish() {
                // 输入完成后我们简单显示一下输入的密码
                // 也就是说——>实现你的交易逻辑什么的在这里写
                toast(pwdView.strPassword!!)
            }

            override fun outfo() {
                //关闭支付页面
                finish()
            }

            override fun forgetPwd() {
                toast("忘记密码")
            }

        })
    }
}
