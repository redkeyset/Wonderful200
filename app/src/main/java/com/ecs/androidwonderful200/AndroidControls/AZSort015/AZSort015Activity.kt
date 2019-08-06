package com.ecs.androidwonderful200.AndroidControls.AZSort015

import android.app.ActionBar
import android.content.Context
import android.graphics.PixelFormat
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.AbsListView
import android.widget.ListView
import android.widget.TextView
import com.ecs.androidwonderful200.R

class AZSort015Activity : AppCompatActivity(), MySideBar.OnTouchingLetterChangedListener {
    //显示城市列表
    private var mainList: ListView? = null
    //城市数据源
    private var data: MutableList<String>? = null
    //字母位置
    private var letterPositionList: MutableList<Int>? = null
    //字母Char
    private var letterCharList: MutableList<Int>? = null
    //自定义View显示右侧字母
    private var myView: MySideBar? = null
    //列表上方固定文字
    private var tv01: TextView? = null
    //右侧显示内容
    private val title = arrayOf("热门城市", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    //城市数据源二维数组
    private val ary = arrayOf(
            //			热门城市
            arrayOf("北京", "上海", "广州", "深圳", "重庆", "三亚", "天津"),
            //			A
            arrayOf("阿坝", "阿拉善", "阿里", "安康", "安庆", "鞍山", "安顺", "安阳", "澳门"),
            //			B
            arrayOf("北京", "白银", "保定", "宝鸡", "保山", "包头", "巴中", "北海", "蚌埠", "本溪", "毕节", "滨州", "百色", "亳州"),
            //			C
            arrayOf("重庆", "成都", "长沙", "长春", "沧州", "常德", "昌都", "长治", "常州", "巢湖", "潮州", "承德", "郴州", "赤峰", "池州", "崇左", "楚雄", "滁州", "朝阳"),
            //			D
            arrayOf("大连", "东莞", "大理", "丹东", "大庆", "大同", "大兴安岭", "德宏", "德阳", "德州", "定西", "迪庆", "东营"),
            //			E
            arrayOf("鄂尔多斯", "恩施", "鄂州"),
            //			F
            arrayOf("福州", "防城港", "佛山", "抚顺", "抚州", "阜新", "阜阳"),
            //			G
            arrayOf("广州", "桂林", "贵阳", "甘南", "赣州", "甘孜", "广安", "广元", "贵港", "果洛"),
            //			H
            arrayOf("杭州", "哈尔滨", "合肥", "海口", "呼和浩特", "海北", "海东", "海南", "海西", "邯郸", "汉中", "鹤壁", "河池", "鹤岗", "黑河", "衡水", "衡阳", "河源", "贺州", "红河", "淮安", "淮北", "怀化", "淮南", "黄冈", "黄南", "黄山", "黄石", "惠州", "葫芦岛", "呼伦贝尔", "湖州", "菏泽"),
            //			I
            arrayOf("暂无"),
            //			J
            arrayOf("济南", "佳木斯", "吉安", "江门", "焦作", "嘉兴", "嘉峪关", "揭阳", "吉林", "金昌", "晋城", "景德镇", "荆门", "荆州", "金华", "济宁", "晋中", "锦州", "九江", "酒泉"),
            //			K
            arrayOf("昆明", "开封"),
            //			L
            arrayOf("兰州", "拉萨", "来宾", "莱芜", "廊坊", "乐山", "凉山", "连云港", "聊城", "辽阳", "辽源", "丽江", "临沧", "临汾", "临夏", "临沂", "林芝", "丽水", "六安", "六盘水", "柳州", "陇南", "龙岩", "娄底", "漯河", "洛阳", "泸州", "吕梁"),
            //			M
            arrayOf("马鞍山", "茂名", "眉山", "梅州", "绵阳", "牡丹江"),
            //			N
            arrayOf("南京", "南昌", "南宁", "宁波", "南充", "南平", "南通", "南阳", "那曲", "内江", "宁德", "怒江"),
            //			O
            arrayOf("暂无"),
            //			P
            arrayOf("盘锦", "攀枝花", "平顶山", "平凉", "萍乡", "莆田", "濮阳"),
            //			Q
            arrayOf("青岛", "黔东南", "黔南", "黔西南", "庆阳", "清远", "秦皇岛", "钦州", "齐齐哈尔", "泉州", "曲靖", "衢州"),
            //			R
            arrayOf("日喀则", "日照"),
            //			S
            arrayOf("上海", "深圳", "苏州", "沈阳", "石家庄", "三门峡", "三明", "三亚", "商洛", "商丘", "上饶", "山南", "汕头", "汕尾", "韶关", "绍兴", "邵阳", "十堰", "朔州", "四平", "绥化", "遂宁", "随州", "宿迁", "宿州"),
            //			T
            arrayOf("天津", "太原", "泰安", "泰州", "台州", "唐山", "天水", "铁岭", "铜川", "通化", "通辽", "铜陵", "铜仁", "台湾"),
            //			U
            arrayOf("暂无"),
            //			V
            arrayOf("暂无"),
            //			W
            arrayOf("武汉", "乌鲁木齐", "无锡", "威海", "潍坊", "文山", "温州", "乌海", "芜湖", "乌兰察布", "武威", "梧州"),
            //			X
            arrayOf("厦门", "西安", "西宁", "襄樊", "湘潭", "湘西", "咸宁", "咸阳", "孝感", "邢台", "新乡", "信阳", "新余", "忻州", "西双版纳", "宣城", "许昌", "徐州", "香港", "锡林郭勒", "兴安"),
            //			Y
            arrayOf("银川", "雅安", "延安", "延边", "盐城", "阳江", "阳泉", "扬州", "烟台", "宜宾", "宜昌", "宜春", "营口", "益阳", "永州", "岳阳", "榆林", "运城", "云浮", "玉树", "玉溪", "玉林"),
            //			Z
            arrayOf("杂多县", "赞皇县", "枣强县", "枣阳市", "枣庄", "泽库县", "增城市", "曾都区", "泽普县", "泽州县", "札达县", "扎赉特旗", "扎兰屯市", "扎鲁特旗", "扎囊县", "张北县", "张店区", "章贡区", "张家港", "张家界", "张家口", "漳平市", "漳浦县", "章丘市", "樟树市", "张湾区", "彰武县", "漳县", "张掖", "漳州", "长子县", "湛河区", "湛江", "站前区", "沾益县", "诏安县", "召陵区", "昭平县", "肇庆", "昭通", "赵县", "昭阳区", "招远市", "肇源县", "肇州县", "柞水县", "柘城县", "浙江", "镇安县", "振安区", "镇巴县", "正安县", "正定县", "正定新区", "正蓝旗", "正宁县", "蒸湘区", "正镶白旗", "正阳县", "郑州", "镇海区", "镇江", "浈江区", "镇康县", "镇赉县", "镇平县", "振兴区", "镇雄县", "镇原县", "志丹县", "治多县", "芝罘区", "枝江市", "芷江侗族自治县", "织金县", "中方县", "中江县", "钟楼区", "中牟县", "中宁县", "中山", "中山区", "钟山区", "钟山县", "中卫", "钟祥市", "中阳县", "中原区", "周村区", "周口", "周宁县", "舟曲县", "舟山", "周至县", "庄河市", "诸城市", "珠海", "珠晖区", "诸暨市", "驻马店", "准格尔旗", "涿鹿县", "卓尼", "涿州市", "卓资县", "珠山区", "竹山县", "竹溪县", "株洲", "株洲县", "淄博", "子长县", "淄川区", "自贡", "秭归县", "紫金县", "自流井区", "资溪县", "资兴市", "资阳"))
    private var lastFirstVisibleItem: Int = 0
    private var overlay: TextView? = null
    private var handler: Handler? = null
    private var overlayThread: OverlayThread? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_azsort015)
        //用于显示城市列表
        mainList = findViewById<ListView>(R.id.mainlist)
        //显示右侧字母列表
        myView = findViewById<MySideBar>(R.id.myview)
        //城市列表上方固定文字
        tv01 = findViewById<TextView>(R.id.main_tv01)
        //选择的城市
        cityTV = findViewById<TextView>(R.id.cityTV)
        //绑定滑动监听
        myView!!.setOnTouchingLetterChangedListener(this)
        data = ArrayList()
        letterCharList = ArrayList()
        letterPositionList = ArrayList()
        //消息通知
        handler = Handler()
        //隐藏提示信息线程
        overlayThread = OverlayThread()
        initOverlay()
        var index = 0
        var position = 0
        letterCharList!!.add(index)
        //循环城市数据源
        for (i in ary.indices) {
            for (j in 0 until ary[i].size) {
                if (i == 0 && j == 0) {
                    index++
                    letterPositionList!!.add(position)
                } else if (j == 0) {
                    letterCharList!!.add(index)
                    letterPositionList!!.add(position)
                    index++
                } else {
                    letterCharList!!.add(-1)
                }
                position++
                //添加数据到集合
                data!!.add(ary[i][j])
            }
        }
        //声明适配器
        val adapter = MyAdapter(this, data!!, letterCharList!!, title)
        //设置适配器
        mainList!!.adapter = adapter
        //判断listview滚动设置城市列表上方固定文字
        mainList!!.setOnScrollListener(object : RecyclerView.OnScrollListener(), AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {}
            //滑动的时候触发
            override fun onScroll(view: AbsListView, firstVisibleItem: Int,
                         visibleItemCount: Int, totalItemCount: Int) {
                if (letterCharList!![firstVisibleItem] >= 0) {
                    //设置显示文字
                    tv01!!.text = title[letterCharList!![firstVisibleItem]]
                    lastFirstVisibleItem = firstVisibleItem
                } else {
                    if (lastFirstVisibleItem > firstVisibleItem) {
                        //设置显示文字
                        tv01!!.text = title[letterCharList!![lastFirstVisibleItem] - 1]
                    }
                }
            }
        })

    }

    //加载提示选择的字母
    private fun initOverlay() {
        //设置选择字母后提示信息显示位置
        val inflater = LayoutInflater.from(this)
        overlay = inflater.inflate(R.layout.overlay, null) as TextView
        overlay!!.visibility = View.INVISIBLE
        //设置提示信息显示的位置
        val lp = WindowManager.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT)
        val windowManager = this
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        //添加提示信息到布局 lp的位置
        windowManager.addView(overlay, lp)
    }

    //触摸监听
    override fun onTouchingLetterChanged(s: Int) {
        //切换到list指定的item位置
        mainList!!.setSelection(letterPositionList!![s])
        //设置提示的字母
        overlay!!.text = title[s]
        //显示提示窗
        overlay!!.visibility = View.VISIBLE
        //移除以前开启的线程
        handler!!.removeCallbacks(overlayThread)
        //1.5秒后执行overlayThread线程 隐藏选择字母提示
        handler!!.postDelayed(overlayThread, 1500)
    }

    //关闭提示信息线程
    private inner class OverlayThread : Runnable {
        override fun run() {
            //隐藏提示信息
            overlay!!.visibility = View.GONE
        }
    }

    companion object {
        lateinit var cityTV: TextView
    }

}