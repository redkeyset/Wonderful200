package com.ecs.wonderful200.sdcardfileinfo025

import android.Manifest
import android.app.AlertDialog
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.view.View
import com.ecs.wonderful200.baselibrary.MPermissionsActivity
import kotlinx.android.synthetic.main.activity_sd_card_file.*
import org.jetbrains.anko.toast
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class SdCardFileActivity : MPermissionsActivity(), View.OnClickListener {
    //创建文件的文件名
    private val FILENAME: String = "filedemo.txt"
    var file: File? = null
    override fun onClick(v: View?) {
        when (v) {
            bt1 -> {
                // 使用SDcard写操作
                //权限判断 没有弹出提示选择
                requestPermission(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0x0001)
            }

            bt2 -> {
                // 使用SDcard读操作
                //权限判断 没有弹出提示选择
                requestPermission(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0x0002);
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sd_card_file)
        initView()
        //初始化文件 在根目录创建目录为filedemo的txt文件
        file = File(Environment.getExternalStorageDirectory(), FILENAME)
    }

    private fun initView() {
        bt1.setOnClickListener(this)
        bt2.setOnClickListener(this)
    }

    override fun permissionSuccess(requestCode: Int) {
        when (requestCode) {
            0x0001 -> {
                // 使用SDcard写操作
                writesd()
            }
            0x0002 -> {
                // 使用SDcard读操作
                readsd()
            }
        }
    }

    //    读取SDcard信息
    private fun readsd() {
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            try {
                val inputStream = FileInputStream(file)
                var b = ByteArray(inputStream.available())
                //读取文件流
                inputStream.read(b)
                val nr = String(b)
                if (!TextUtils.isEmpty(nr)) {
                    AlertDialog.Builder(this)
                            .setTitle("读取文档信息")
                            .setMessage("文档内容：$nr")
                            .setCancelable(false)
                            .setPositiveButton("知道了!") { dialog, _ ->
                                dialog.cancel()
                            }
                            .create().show()
                } else {

                }
            } catch (e: Exception) {
                toast("读取失败")
            }
        } else {
            // 此时SDcard不存在或者不能进行读写操作的
            toast("此时SDcard不存在或者不能进行读写操作")
        }
    }

    //    写入SDcard信息
    private fun writesd() {
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            try {
                val fos = FileOutputStream(file)
                fos.write(et1.text.toString().toByteArray())
                //关闭写入流
                fos.close()
                toast("写入文件成功!")
            } catch (e: Exception) {
                toast("写入文件失败")
            }
        } else {
            toast("此时SDcard不存在或者不能进行读写操作")
        }
    }
}
