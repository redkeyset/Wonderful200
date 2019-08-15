package com.ecs.wonderful200.sdcardfileinfo025

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log


open class MPermissionsActivity : AppCompatActivity() {
    private var REQUEST_CODE_PERMISSION = 0x00099
    private var TAG = "MPermissionsActivity"

    /**
     * 请求权限
     */
    fun requestPermission(permissions: Array<String>, requestCode: Int) {
        this.REQUEST_CODE_PERMISSION = requestCode
        if (checkPermissions(permissions)) {
            permissionSuccess(REQUEST_CODE_PERMISSION)
        } else {
            val needPermissions = getDeniedPermissions(permissions)
            ActivityCompat.requestPermissions(this, needPermissions.toTypedArray(), REQUEST_CODE_PERMISSION)
        }
    }

    /**
     * 检测所有的权限是否都已授权
     */
    private fun checkPermissions(permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }

        permissions.forEach {
            if (ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun getDeniedPermissions(permissions: Array<String>): List<String> {
        val needRequestPermissionList = arrayListOf<String>()
        permissions.forEach {
            if (ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, it)) {
                needRequestPermissionList?.add(it)
            }
        }

        return needRequestPermissionList!!
    }



    open fun permissionSuccess(requestCode: Int) {
        Log.v(TAG, "获取权限成功 $requestCode")
    }

    private fun permissionFail(requestCode: Int) {
        Log.v(TAG, "获取权限失败 $requestCode")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (verifyPermissions(grantResults)) {
                permissionSuccess(REQUEST_CODE_PERMISSION)
            } else {
                permissionFail(REQUEST_CODE_PERMISSION)
                showTipsDialog()
            }
        }
    }

    /**
     * 显示提示对话框
     */
    private fun showTipsDialog() {
        AlertDialog.Builder(this)
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setCancelable(false)
                .setNegativeButton("取消") { dialog, which ->
                    dialog.cancel()
                }.setPositiveButton("确定") { dialog, which ->
                    startAppSettings()
                }.create().show()
    }

    private fun startAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }

    /**
     * 确认所有的权限是否都已授权
     */
    private fun verifyPermissions(grantResults: IntArray): Boolean {
        grantResults.forEach {
            if (it != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }
}
