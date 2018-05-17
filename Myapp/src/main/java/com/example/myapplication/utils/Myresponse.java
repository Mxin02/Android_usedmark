package com.example.myapplication.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by L on 2017/5/4.
 */

public class Myresponse implements OnResponseListener {
    private final Context context;
    private final MyListener listener;
    private ProgressDialog mProgressDialog;


    public Myresponse(Context context, MyListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void onStart(int what) {
//        startDialog();
    }

    public void startDialog() {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle("提示");
        mProgressDialog.setMessage("正在加载......");
        mProgressDialog.show();
    }

    public void stopDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onSucceed(int i, Response response) {
        listener.onSuccess(i, response);
    }

    @Override
    public void onFailed(int i, Response response) {
        Toast.makeText(context, "加载失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFinish(int what) {

    }
}
