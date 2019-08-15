package com.ecs.androidwonderful200

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.ecs.androidwonderful200.AndroidControls.AZSort015.AZSort015Activity
import com.ecs.androidwonderful200.AndroidControls.ImageFDScrollView016.ImageFDScrollViewActivity
import com.ecs.androidwonderful200.AndroidControls.ProgressBar013.ProgressBar013Activity
import com.ecs.androidwonderful200.AndroidControls.ZFPlay014.ZFPlay014Activity
import com.ecs.androidwonderful200.menudialog.AnimDialog05.AnimDialog05Activity
import com.ecs.androidwonderful200.menudialog.AnnouncementDrawer02.AnnouncementDrawer02Activity
import com.ecs.androidwonderful200.menudialog.CircleMenu08.CircleMenu08Activity
import com.ecs.androidwonderful200.menudialog.CustomToast11.CustomToast11Activity
import com.ecs.androidwonderful200.menudialog.DownMenu12.DownMenu12Activity
import com.ecs.androidwonderful200.menudialog.MessageNotifcation06.MessageNotifcation06Activity
import com.ecs.androidwonderful200.menudialog.QQDialog07.QQDialog07Activity
import com.ecs.androidwonderful200.menudialog.QQMenu03.QQMenu03Activity
import com.ecs.androidwonderful200.menudialog.RainbowMenu09.RainbowMenu09Activity
import com.ecs.androidwonderful200.menudialog.SatellistMenu04.SatellistMenu04Activity
import com.ecs.androidwonderful200.menudialog.SlideDeleteMenu10.SlideDeleteMenu10Activity
import com.ecs.androidwonderful200.menudialog.weimenu01.WeiMenu01Activity
import com.ecs.wonderful200.calendars.CalendarsActivity
import com.ecs.wonderful200.sdcardfileinfo025.SdCardFileActivity
import com.ecs.wonderful200.sharedpreferences024.SharedPreferActivity
import com.ecs.wonderful200.sqllitenotes026.SqlliteNotesActivity
import com.ecs.wonderful200.timedialog018.TimeDialogActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.wonderful01.*
import kotlinx.android.synthetic.main.wonderful02.*
import kotlinx.android.synthetic.main.wonderful03.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val childCount = llContent.childCount
        for (item in 0..(childCount - 1)) {
            val childAt = llContent.getChildAt(item)
            if (childAt is Button) { // 先判断 llContent 的子是不是Button，如果是Button 就设置点击
                childAt.setOnClickListener(this)
            } else if (childAt is LinearLayout) {   //如果是 LinearLayout，因为include的布局，这里都是LinearLayout，就进行再次遍历
                for (item1 in 0..(childAt.childCount - 1)) {
                    val childAt1 = childAt.getChildAt(item1)
                    if (childAt1 is Button) {
                        childAt1.setOnClickListener(this)
                    }
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            // 第1章
            btnWeiMenu -> startActivity(intentFor<WeiMenu01Activity>())
            btnAnnouncementDrawer -> startActivity(intentFor<AnnouncementDrawer02Activity>())
            btnQQMenu -> startActivity(intentFor<QQMenu03Activity>())
            btnSatelListMenu -> startActivity(intentFor<SatellistMenu04Activity>())
            btnAnimDialog -> startActivity(intentFor<AnimDialog05Activity>())
            btnMessageNotifcation -> startActivity(intentFor<MessageNotifcation06Activity>())
            btnQQDialog -> startActivity(intentFor<QQDialog07Activity>())
            btnCircleMenu -> startActivity(intentFor<CircleMenu08Activity>())
            btnRainbowMenu -> startActivity(intentFor<RainbowMenu09Activity>())
            btnSlideDeleteMenu -> startActivity(intentFor<SlideDeleteMenu10Activity>())
            btnCustomToast -> startActivity(intentFor<CustomToast11Activity>())
            btnDownMenu -> startActivity(intentFor<DownMenu12Activity>())

            // 第2章
            btnProgressBar -> startActivity(intentFor<ProgressBar013Activity>())
            btnZFPlay -> startActivity(intentFor<ZFPlay014Activity>())
            btnAZSort -> startActivity(intentFor<AZSort015Activity>())
            btnImageFDScrollView -> startActivity(intentFor<ImageFDScrollViewActivity>())
            btnCalendars -> startActivity(intentFor<CalendarsActivity>())
            btnTimeDialog -> startActivity(intentFor<TimeDialogActivity>())

            //第3章
            btnSharedPrefer -> startActivity(intentFor<SharedPreferActivity>())
            btnSdCardFile -> startActivity(intentFor<SdCardFileActivity>())
            btnSqlliteNotes -> startActivity(intentFor<SqlliteNotesActivity>())

        }
    }
}
