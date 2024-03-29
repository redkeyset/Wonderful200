package com.ecs.wonderful200.localpicture027;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.ecs.wonderful200.baselibrary.MPermissionsActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LocalPictureActivity extends MPermissionsActivity {
    //图片集合
    private HashMap<String, List<String>> mGruopMap = new HashMap<String, List<String>>();
    //子图片数组集合
    private List<ImageBean> list;
    //消息标识
    private final static int SCAN_OK = 1;
    //进度显示框
    private ProgressDialog mProgressDialog;
    //声明适配器 用于装载图片
    private GroupAdapter adapter;
    private GridView mGroupGridView;
    //用于接收消息更新页面
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SCAN_OK:
                    //关闭进度条
                    mProgressDialog.dismiss();
                    //初始化适配器
                    adapter = new GroupAdapter(LocalPictureActivity.this, list = subGroupOfImage(mGruopMap), mGroupGridView);
                    //绑定适配器
                    mGroupGridView.setAdapter(adapter);
                    break;
            }
        }

    };

    public LocalPictureActivity() {
        list = new ArrayList<ImageBean>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_local_picture);
        //初始化控件
        mGroupGridView = (GridView) findViewById(R.id.main_grid);
        //判断权限 动态获取
        requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x0001);
    }

    /**
     * 权限成功回调函数
     *
     * @param requestCode
     */
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
        switch (requestCode) {
            case 0x0001:
                getImages();
                //设置点击事件
                mGroupGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        List<String> childList = mGruopMap.get(list.get(position).getFolderName());
                        //进入到显示图片页
                        Intent mIntent = new Intent(LocalPictureActivity.this, ShowImageActivity.class);
                        //传递的图片数组信息
                        mIntent.putStringArrayListExtra("data", (ArrayList<String>) childList);
                        //开始跳转页面
                        startActivity(mIntent);
                    }
                });
                break;
        }
    }

    //利用ContentProvider扫描手机中的图片，此方法在运行在子线程中
    private void getImages() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }
        //显示进度条
        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = LocalPictureActivity.this.getContentResolver();
                //只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);

                while (mCursor.moveToNext()) {
                    //获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    //获取该图片的父路径名
                    String parentName = new File(path).getParentFile().getName();
                    //根据父路径名将图片放入到mGruopMap中
                    if (!mGruopMap.containsKey(parentName)) {
                        List<String> chileList = new ArrayList<String>();
                        chileList.add(path);
                        mGruopMap.put(parentName, chileList);
                    } else {
                        mGruopMap.get(parentName).add(path);
                    }
                }
                mCursor.close();
                //通知Handler扫描图片完成
                mHandler.sendEmptyMessage(SCAN_OK);
            }
        }).start();
    }


    /**
     * 组装分组界面GridView的数据源，因为我们扫描手机的时候将图片信息放在HashMap中
     * 所以需要遍历HashMap将数据组装成List
     */
    private List<ImageBean> subGroupOfImage(HashMap<String, List<String>> mGruopMap) {
        if (mGruopMap.size() == 0) {
            return null;
        }
        List<ImageBean> list = new ArrayList<ImageBean>();
        Iterator<Map.Entry<String, List<String>>> it = mGruopMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<String>> entry = it.next();
            ImageBean mImageBean = new ImageBean();
            String key = entry.getKey();
            List<String> value = entry.getValue();
            mImageBean.setFolderName(key);
            mImageBean.setImageCounts(value.size());
            //获取该组的第一张图片
            mImageBean.setTopImagePath(value.get(0));
            list.add(mImageBean);
        }
        return list;
    }
}
