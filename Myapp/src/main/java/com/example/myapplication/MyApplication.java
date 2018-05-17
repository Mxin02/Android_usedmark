package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.db.DbTools;
import com.example.myapplication.db.Goods;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.yanzhenjie.nohttp.NoHttp;


import cn.bmob.sms.BmobSMS;


/**
 * Created by L on 2017/4/29.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        NoHttp.initialize(this);
        Fresco.initialize(this);

        //62f79064bc2e4d00c92b961e3045a835
        //949f62d128a4b1086746fa85ca108320  自己
        BmobSMS.initialize(this, "62f79064bc2e4d00c92b961e3045a835");
//        Bmob.initialize(this,"62f79064bc2e4d00c92b961e3045a835");


    }

}
