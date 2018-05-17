package com.example.myapplication.Acitivity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Listener.onTextSendComplementListener;
import com.example.myapplication.R;
import com.example.myapplication.adapter.RecorderAdapter;
import com.example.myapplication.base.Response;
import com.example.myapplication.base.Text;
import com.example.myapplication.base.tulingbean;
import com.example.myapplication.utils.ListUtils;
import com.google.gson.Gson;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.myapplication.fragment.FindFragment.FAULT;

public class ChatActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener, onTextSendComplementListener, View.OnTouchListener {
    private static final int SUECCESS = 324;
    private static final int FAULTSS = 5354;
    private static final int FMSG = 909;

    private ListView mListview;
    private Button send;
    private Boolean isVisible;
    private InputMethodManager inputMethodManager;
    private onTextSendComplementListener sendComplementListener;
    private String currentTime = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            int what = msg.what;
            switch (what) {
                case SUECCESS:
                    Log.i("lei", "网络连接正常");
                    String message = (String) msg.obj;
                    response(message);
                    break;
                case FAULTSS:
                    Log.i("lei", "网络连接失败");
                    Toast.makeText(ChatActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                    break;
                case FMSG:
                    String obj = (String) msg.obj;
                    if (sendComplementListener != null) {
                        sendComplementListener.finishComplement(obj);
                    }
                    break;
            }


        }
    };
    private EditText et;
    private SimpleDateFormat dateFormat;
    private ImageView chat_back;
    private RecorderAdapter mAdapter = null;
    private int id;
    private List list;
    private BroadcastReceiver connectionReceiver;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        id = getIntent().getIntExtra("id", -1);
        mListview = (ListView) findViewById(R.id.id_listview);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        et = (EditText) findViewById(R.id.id_et);
        send = (Button) findViewById(R.id.ed_btn_right);
        chat_back = (ImageView) findViewById(R.id.chat_back);

        et.addTextChangedListener(this);
        send.setEnabled(false);

        chat_back.setOnClickListener(this);
        send.setOnClickListener(this);

        dateFormat = new SimpleDateFormat("HH:mm");
        list = ListUtils.readList(id);

        if (list == null) {
            list = new ArrayList();
            list.clear();
            Log.i("lei", "list1=" + list.size());
        }
        Log.i("lei", "list2=" + list.size());

        mAdapter = new RecorderAdapter(ChatActivity.this, list);
        mListview.setSelection(list.size() - 1);
        mListview.setAdapter(mAdapter);
        mListview.setOnTouchListener(this);
    }


    @Override
    public void onBackPressed() {
        id = getIntent().getIntExtra("id", -1);
        Log.i("lei", "id2=" + id);
        Log.i("lei", "list3=" + list.size());
        if (id != -1) {
            ListUtils.saveList(list, id);
        }
        super.onBackPressed();

    }

    public void setOnSendComplementListener(onTextSendComplementListener listener) {
        sendComplementListener = listener;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.ed_btn_right:
                if (currentTime != null) {
                    String date = getTime();
                    if (date.equals(currentTime)) {
                        isVisible = false;
                    } else {
                        isVisible = true;
                        currentTime = date;
                    }
                } else {
                    String date = getTime();
                    currentTime = date;
                    isVisible = true;
                }
                String message = et.getText().toString().trim();


                initData(message);


                String time = getTime();
                Text text = new Text(message, time, isVisible, true);
                list.add(text);
                et.setText("");
                setOnSendComplementListener(this);
                mAdapter.notifyDataSetChanged();
                mListview.setSelection(list.size() - 1);
                break;
            case R.id.chat_back:
                onBackPressed();
                break;
        }
    }

    /**
     * 获取时间的方法
     *
     * @return
     */
    private String getTime() {
        String format;
        Date date = new Date();
        if (dateFormat != null) {
            format = dateFormat.format(date);
        } else {
            format = null;
        }
        return format;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        send.setEnabled(false);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (et.getText().toString().trim().equals("")) {
            send.setEnabled(false);
        } else {
            send.setEnabled(true);
        }
    }


    @Override
    public void afterTextChanged(Editable editable) {
        if (et.getText().toString().trim().equals("")) {
            send.setEnabled(false);
        } else {
            send.setEnabled(true);
        }
    }

    @Override
    public void finishComplement(String message) {
        if (message != null) {
            Response response = new Response(message, getTime(), false);
            list.add(response);
            mListview.setSelection(list.size() - 1);
        }
    }


    public void initData(final String message) {
        // unconnect network 这时提示用户网络断开信息
// connect network   可以做一些网络请求，刷新界面
        connectionReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ConnectivityManager connectMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
                    // unconnect network 这时提示用户网络断开信息
                    handler.sendEmptyMessage(FAULTSS);
                } else if (!mobNetInfo.isConnected() && wifiNetInfo.isConnected()) {
                    new Thread() {
                        @Override
                        public void run() {
                            boolean isUseFul = isNetworkOnline();
                            if (isUseFul) {
                                Message obtain = Message.obtain();
                                obtain.what = SUECCESS;
                                obtain.obj = message;
                                handler.sendMessage(obtain);
                            } else {
                                handler.sendEmptyMessage(FAULTSS);
                            }
                        }

                    }.start();
                } else {
                    // connect network   可以做一些网络请求，刷新界面
                    Message obtain = Message.obtain();
                    obtain.what = SUECCESS;
                    obtain.obj = message;
                    handler.sendMessage(obtain);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectionReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connectionReceiver != null) {
            unregisterReceiver(connectionReceiver);
        }

    }

    public boolean isNetworkOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("ping -c 3 www.baidu.com");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 返回从网络取出来的数据
     *
     * @return
     */
    private void response(final String message) {

        new Thread() {
            @Override
            public void run() {
                String data = null;
                try {
                    //一定要将其变成utf-8
                    data = URLEncoder.encode(message, "utf-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    String strUrl = "http://www.tuling123.com/openapi/api?key=2e29921641b808e1986e20d4fc6e3238&info=" + data;
                    //key是你自己注册得到的，当然你直接用我的也行
                    URL url = null;
                    url = new URL(strUrl);
                    HttpURLConnection conn = null;
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko");
                    conn.connect();
                    if (conn.getResponseCode() == 200) {
                        InputStream inStream = conn.getInputStream();
                        //打开这个页面的输入流，这个网站的内容以字节流的形式返回。如果是网页就返回html，图片就返回图片的内容。
                        byte[] buf = new byte[1024];
                        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                        int n = 0;
                        while ((n = inStream.read(buf)) != -1) {
                            outStream.write(buf, 0, n);
                        }
                        inStream.close();
                        outStream.close();
//用ByteArrayOutputStream全部缓冲好后再一次转成String，不然再间隔的地方会出现乱码问题
                        String result = outStream.toString();
//返回的JSON，弄成字符串后去掉头和尾就行
                        Gson gson = new Gson();
                        tulingbean bean = gson.fromJson(result, tulingbean.class);
                        String url1 = bean.getUrl();
                        result = bean.getText();
                        StringBuilder sb = new StringBuilder(result);
                        if (url1 != null) {
                            sb.append("\r\n打开界面 :" + url1);
                        }

                        Message msg = Message.obtain();
                        msg.obj = sb.toString();
                        msg.what = FMSG;
                        handler.sendMessage(msg);
                    } else {
                        Log.i("lei", "服务器异常");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        inputMethodManager.hideSoftInputFromWindow(et.getWindowToken(), 0);
        return false;
    }
}
