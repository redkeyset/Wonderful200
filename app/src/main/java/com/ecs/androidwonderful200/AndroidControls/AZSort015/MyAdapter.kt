package com.ecs.androidwonderful200.AndroidControls.AZSort015

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ecs.androidwonderful200.R

/**
 * 作者：RedKeyset on 2019/8/6 14:02
 * 邮箱：redkeyset@aliyun.com
 */
class MyAdapter(private val context: Context, private val data: List<String>,
                private val letterCharList: List<Int>, private val title: Array<String>) : BaseAdapter() {
    private var mLayoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (mLayoutInflater == null) {
            mLayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = mLayoutInflater!!.inflate(R.layout.maillist_item, null, false)
        }
        val tv02 = convertView!!.findViewById(R.id.mainlist_item_tv01) as TextView
        //判断是否显示分类提示
        if (letterCharList[position] >= 0) {
            tv02.visibility = View.VISIBLE
            tv02.text = title[letterCharList[position]]
        } else {
            tv02.visibility = View.GONE
        }
        tv02.setTextColor(Color.BLACK)
        val tv01 = convertView.findViewById(R.id.mainlist_item_tv02) as TextView
        //设置显示城市内容
        tv01.text = data[position]
        tv01.setTextColor(Color.BLACK)
        //绑定列表点击方法
        convertView.setOnClickListener(object : DialogInterface.OnClickListener, View.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {

            }

            override fun onClick(v: View?) {
                //设置当前定位城市
                AZSort015Activity.cityTV.setText(data[position])
            }
        })
        return convertView
    }

}
