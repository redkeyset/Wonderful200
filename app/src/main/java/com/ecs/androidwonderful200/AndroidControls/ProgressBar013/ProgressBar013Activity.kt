package com.ecs.androidwonderful200.AndroidControls.ProgressBar013

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ecs.androidwonderful200.R
import kotlinx.android.synthetic.main.activity_progress_bar013.*
import org.jetbrains.anko.toast

class ProgressBar013Activity : AppCompatActivity(), View.OnClickListener {
    lateinit var mDialog: AlertDialog
    var handler: Handler = Handler()

    override fun onClick(v: View?) {
        when (v) {
            btnAnim -> {
                //显示动画进度条
                showRoundProcessDialog(this, R.layout.loading_process_dialog_anim)
            }

            btnColor -> {
                showRoundProcessDialog(this, R.layout.loading_process_dialog_color)
            }

            btnIcon -> {
                showRoundProcessDialog(this, R.layout.loading_process_dialog_icon)
            }
        }

        dismissDialog()
    }

    private fun showRoundProcessDialog(mContext: Context, layout: Int) {
        mDialog = AlertDialog.Builder(mContext).create()
        mDialog.show()
        mDialog.setContentView(layout)
    }

    /**
     * 延迟3s 关闭
     */
    private fun dismissDialog() {
        handler.postDelayed(Runnable {
            kotlin.run {
                toast("mDialog")
                mDialog.dismiss()
            }
        }, 3 * 1000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar013)
        init()
    }

    private fun init() {
        btnAnim.setOnClickListener(this)
        btnColor.setOnClickListener(this)
        btnIcon.setOnClickListener(this)
    }
}
