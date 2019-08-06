package com.ecs.androidwonderful200.AndroidControls.ZFPlay014

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ecs.androidwonderful200.R
import kotlinx.android.synthetic.main.layout_popup_bottom.view.*


/**
 * 作者：RedKeyset on 2019/8/5 17:27
 * 邮箱：redkeyset@aliyun.com
 */

class PasswordView constructor(internal var context: Context, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    //获取输入的密码
    var strPassword: String? = null // 输入的密码
    var tvList: Array<TextView?> // 就6个输入框不会变了，用数组内存申请固定空间，比List省空间
    private val gridView: GridView // 用GrideView布局键盘，其实并不是真正的键盘，只是模拟键盘的功能
    var valueList: ArrayList<Map<String, String>>? = null// 要用Adapter中适配，用数组不能往adapter中填充
    val tvCancel: ImageView//取消按钮
    var currentIndex = -1 // 用于记录当前输入密码格位置
    // GrideView的适配器
    private var adapter: BaseAdapter = object : BaseAdapter() {
        override fun getCount(): Int {
            return valueList!!.size
        }

        override fun getItem(position: Int): Any {
            return valueList!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            var convertView = convertView
            val viewHolder: ViewHolder
            if (convertView == null) {
                //装载数字键盘布局
                convertView = View.inflate(context, R.layout.item_gride, null)
                viewHolder = ViewHolder()
                //初始化键盘按钮
                viewHolder.btnKey = convertView!!
                        .findViewById(R.id.btn_keys)
                convertView!!.tag = viewHolder
            } else {
                viewHolder = convertView!!.tag as ViewHolder
            }
            //设置按钮显示数字
            viewHolder.btnKey!!.text = valueList!![position]["name"]
            if (position == 9) {
                //设置按钮背景
                viewHolder.btnKey!!
                        .setBackgroundResource(R.drawable.selector_key_del)
                //设置按钮不可点击
                viewHolder.btnKey!!.isEnabled = false
            }
            if (position == 11) {
                //设置按钮背景
                viewHolder.btnKey!!
                        .setBackgroundResource(R.drawable.selector_key_del)
            }
            return convertView
        }
    }

    init {
        //view布局
        val view = View.inflate(context, R.layout.layout_popup_bottom, null)
        tvList = arrayOfNulls<TextView>(6)
        valueList = ArrayList()
        //初始化控件
        tvCancel = view.findViewById(R.id.tvCancel) as ImageView
        tvList[0] = view.tv_pass1 as TextView
        tvList[1] = view.tv_pass2 as TextView
        tvList[2] = view.tv_pass3 as TextView
        tvList[3] = view.tv_pass4 as TextView
        tvList[4] = view.tv_pass5 as TextView
        tvList[5] = view.tv_pass6 as TextView
        //初始化键盘
        gridView = view.findViewById(R.id.gv_keybord)
        //设置键盘显示按钮到集合
        setView()
        // 必须要，不然不显示控件
        addView(view)
    }

    //设置按钮显示内容
    private fun setView() {
        // 初始化按钮上应该显示的数字
        for (i in 1..12) {
            val map = HashMap<String, String>()
            if (i < 10) {
                map["name"] = i.toString()
            } else if (i == 10) {
                map["name"] = ""
            } else if (i == 12) {
                map["name"] = "<<-"
            } else if (i == 11) {
                map["name"] = 0.toString()
            }
            valueList!!.add(map)
        }
        //为键盘gridview设置适配器
        gridView.adapter = adapter
        //为键盘按键添加点击事件
        gridView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View,
                                     position: Int, id: Long) {
                // 点击0~9按钮
                if (position < 11 && position != 9) {
                    // 判断输入位置————要小心数组越界
                    if (currentIndex >= -1 && currentIndex < 5) {
                        tvList[++currentIndex]!!.text = valueList!![position]["name"]
                    }
                } else {
                    // 点击退格键
                    if (position == 11) {
                        // 判断是否删除完毕————要小心数组越界
                        if (currentIndex - 1 >= -1) {
                            tvList[currentIndex--]!!.text = ""
                        }
                    }
                }
            }
        }
    }

    // 设置监听方法，在第6位输入完成后触发
    fun setOnFinishInput(pass: OnPasswordInputFinish) {
        tvList[5]!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                       count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                if (s.toString().length == 1) {
                    // 每次触发都要先将strPassword置空，再重新获取，避免由于输入删除再输入造成混乱
                    strPassword = ""
                    for (i in 0..5) {
                        strPassword += tvList[i]!!.text.toString().trim { it <= ' ' }
                    }
                    // 接口中要实现的方法，完成密码输入完成后的响应逻辑
                    pass.inputFinish()
                }
            }
        })
        tvCancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                //点击取消调用接口
                pass.outfo()
            }
        })

        //忘记密码按钮
        tv_forgetPwd.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                //单击忘记密码调用接口
                pass.forgetPwd()
            }
        })
    }

    // 存放控件
    inner class ViewHolder {
        var btnKey: TextView? = null
    }
}