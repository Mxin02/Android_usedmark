package com.example.myapplication.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**
 * Created by L on 2017/5/4.
 */

public class HttpUtils {

    private  HttpUtils() {

    }
    public static void get(final Context context,String url,int what,MyListener responseListener){
        //创建请求队列
        RequestQueue queue = NoHttp.newRequestQueue();
        final Request<String> request = NoHttp.createStringRequest(url, RequestMethod.GET);
        queue.add(what,request,new Myresponse(context,responseListener));
    }
}
