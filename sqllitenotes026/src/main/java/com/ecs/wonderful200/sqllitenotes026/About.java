package com.ecs.wonderful200.sqllitenotes026;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

//关于页面
public class About extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    //返回键功能
    public void onBack(View v) {
        About.this.finish();//关闭关于页面
    }
}
