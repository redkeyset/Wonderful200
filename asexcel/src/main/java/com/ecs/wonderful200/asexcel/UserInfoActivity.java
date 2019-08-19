package com.ecs.wonderful200.asexcel;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.ecs.wonderful200.asexcel.bean.BillObject;
import com.ecs.wonderful200.asexcel.excel.ExcelUtils;
import com.ecs.wonderful200.asexcel.familybill.BillAdapter;

import java.io.File;
import java.util.ArrayList;

/**
 * 作者：RedKeyset on 2019/8/19 11:28
 * 邮箱：redkeyset@aliyun.com
 */
public class UserInfoActivity extends AppCompatActivity{
    private ListView bill_listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //初始化列表用于显示信息
        bill_listview = (ListView) findViewById(R.id.bill_listview);
        //创建view
        View contentHeader = LayoutInflater.from(this).inflate(
                R.layout.listview_header, null);
        //给列表添加头部布局
        bill_listview.addHeaderView(contentHeader);
        into2();
    }
    //获取数据添加到数组中
    public void into2() {
        //获取数据添加到数组中
        ArrayList<BillObject> billList = (ArrayList<BillObject>) ExcelUtils
                .read2DB(new File(getSDPath() + "/Family/bill.xls"), this);
        //绑定适配器
        bill_listview.setAdapter(new BillAdapter(this, billList));
    }
    //得到sd卡目录
    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        }
        String dir = sdDir.toString();
        //返回路径
        return dir;
    }
    //返回
    public void onBack(View view){
        finish();
    }
}
