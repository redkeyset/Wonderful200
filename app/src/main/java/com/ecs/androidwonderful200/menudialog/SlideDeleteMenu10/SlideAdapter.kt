package com.ecs.androidwonderful200.menudialog.SlideDeleteMenu10

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ecs.androidwonderful200.R
import android.view.LayoutInflater

//上下文
class SlideAdapter(private val lContext: Context) : RecyclerView.Adapter<SlideAdapter.MyViewHolder>() {
    //图标数组
    private val icons = intArrayOf(R.drawable.slide_delete_icon_1, R.drawable.slide_delete_icon_2, R.drawable.slide_delete_icon_3, R.drawable.slide_delete_icon_4, R.drawable.slide_delete_icon_5, R.drawable.slide_delete_icon_6, R.drawable.slide_delete_icon_7, R.drawable.slide_delete_icon_8, R.drawable.slide_delete_icon_9, R.drawable.slide_delete_icon_10, R.drawable.slide_delete_icon_11, R.drawable.slide_delete_icon_1, R.drawable.slide_delete_icon_2, R.drawable.slide_delete_icon_3, R.drawable.slide_delete_icon_4, R.drawable.slide_delete_icon_5, R.drawable.slide_delete_icon_6, R.drawable.slide_delete_icon_7, R.drawable.slide_delete_icon_8, R.drawable.slide_delete_icon_9, R.drawable.slide_delete_icon_10, R.drawable.slide_delete_icon_11)
    //名字数组
    private val names = intArrayOf(R.string.slide_delete_name1, R.string.slide_delete_name2, R.string.slide_delete_name3, R.string.slide_delete_name4, R.string.slide_delete_name5, R.string.slide_delete_name6, R.string.slide_delete_name7, R.string.slide_delete_name8, R.string.slide_delete_name9, R.string.slide_delete_name10, R.string.slide_delete_name11, R.string.slide_delete_name1, R.string.slide_delete_name2, R.string.slide_delete_name3, R.string.slide_delete_name4, R.string.slide_delete_name5, R.string.slide_delete_name6, R.string.slide_delete_name7, R.string.slide_delete_name8, R.string.slide_delete_name9, R.string.slide_delete_name10, R.string.slide_delete_name11)
    //信息数组
    private val infos = intArrayOf(R.string.slide_delete_1, R.string.slide_delete_2, R.string.slide_delete_3, R.string.slide_delete_4, R.string.slide_delete_5, R.string.slide_delete_6, R.string.slide_delete_7, R.string.slide_delete_8, R.string.slide_delete_9, R.string.slide_delete_10, R.string.slide_delete_11, R.string.slide_delete_1, R.string.slide_delete_2, R.string.slide_delete_3, R.string.slide_delete_4, R.string.slide_delete_5, R.string.slide_delete_6, R.string.slide_delete_7, R.string.slide_delete_8, R.string.slide_delete_9, R.string.slide_delete_10, R.string.slide_delete_11)

    //图标集合
    private val listIcon = ArrayList<Int>()
    //名称集合
    private val listName = ArrayList<Int>()
    //信息集合
    private val listInfo = ArrayList<Int>()

    init {
        //设置菜单行数与行内图标、名称、信息
        for (i in 0..(icons.size - 1)) {
            listIcon.add(icons[i])
            listName.add(names[i])
            listInfo.add(infos[i])
        }
    }

    /**
     * 返回数据集中的项目总数
     */
    override fun getItemCount(): Int {
        return listIcon.size
    }

    /**
     * 设置列表菜单中子项所显示的内容
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //设置图标
        holder.img.setBackgroundResource(listIcon[position])
        //设置名称
        holder.name.setText(listName[position])
        //设置信息
        holder.info.setText(listInfo[position])

        //设置内容布局的宽为屏幕宽度
        holder.layout_content.layoutParams.width = Utils.getScreenWidth(lContext)

        //删除按钮的事件，单击后删除整行
        holder.btn_Delete.setOnClickListener {
            val n = holder.layoutPosition     //获取要删除行的位置
            removeData(n)                          //删除列表中的子项
        }
    }

    override fun onCreateViewHolder(arg0: ViewGroup, arg1: Int): MyViewHolder {
        //获取列表中，每行的布局文件
        val view = LayoutInflater.from(lContext).inflate(R.layout.slidedelete_layout_item, arg0, false)
        return MyViewHolder(view)
    }

    //获取相关控件
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btn_Delete: TextView             //删除按钮
        var name: TextView
        var info: TextView               //编号文字
        var img: ImageView                   //图标
        var layout_content: ViewGroup       //图标与编号的布局

        init {
            btn_Delete = itemView.findViewById(R.id.tv_delete) as TextView
            name = itemView.findViewById(R.id.name) as TextView
            info = itemView.findViewById<View>(R.id.info) as TextView
            img = itemView.findViewById(R.id.img) as ImageView
            layout_content = itemView.findViewById(R.id.layout_content) as ViewGroup
        }
    }

    /**
     * 删除列表中子项
     */
    fun removeData(position: Int) {
        listIcon.removeAt(position)   //删除子项中显示图标
        listName.removeAt(position)   //删除名字
        listInfo.removeAt(position)   //删除信息
        notifyItemRemoved(position)    //删除列表
    }

}