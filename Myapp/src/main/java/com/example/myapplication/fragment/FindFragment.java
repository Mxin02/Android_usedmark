package com.example.myapplication.fragment;


import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.Acitivity.NewDetaiActivity;
import com.example.myapplication.R;
import com.example.myapplication.base.JuHeBean;
import com.example.myapplication.base.baseFragment;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.MyListener;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindFragment extends baseFragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView find_listview;
    private List mlist = new ArrayList();
    private adapter mAdapter;
    private Gson mGson = null;
    private ProgressDialog mProgressDialog;
    private String url = "http://v.juhe.cn/toutiao/index?type=top&key=266d56c73ed4e45a8e69a1cb8ba9df54";
    private RelativeLayout fauild;
    public static final int FAULT = 12;
    public static final int SUECCESS = 0;
    public static final int FAULT_USEFUL = 3;

    private BroadcastReceiver connectionReceiver;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case SUECCESS:  //发送网络请求
                    fauild.setVisibility(View.GONE);
                    find_listview.setVisibility(View.VISIBLE);
                    String result = mSpUtils.getString(url, null);
                    stopDialog();
                    if (result != null) {
                        prograssDate(result);
                    } else {
                        HttpUtils.get(mContext, url, 1, new MyNetListener());
                    }

                    break;
                case FAULT:
                    stopDialog();
                    fauild.setVisibility(View.VISIBLE);
                    find_listview.setVisibility(View.GONE);
                    Toast.makeText(mContext, "网络连接中断，请重新检查网络是否正常", Toast.LENGTH_SHORT).show();
                    break;
                case FAULT_USEFUL:
                    stopDialog();
                    fauild.setVisibility(View.VISIBLE);
                    find_listview.setVisibility(View.GONE);
                    Toast.makeText(mContext, "网络连接不可用", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public View initView() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.fragment_find, null);
        find_listview = (ListView) inflate.findViewById(R.id.find_listview);
        fauild = (RelativeLayout) inflate.findViewById(R.id.fauild);
        Button find_init = (Button) inflate.findViewById(R.id.find_init);

        // 为listivew设置点击事件
        find_listview.setOnItemClickListener(this);
        find_init.setOnClickListener(this);
        return inflate;
    }

    @Override
    public void setTitleView(TextView title, ImageView back) {
        title.setText("发现");
        back.setVisibility(View.GONE);
    }


    /**
     * 点击事件
     *
     * @param adapterView
     * @param view
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        // TODO Auto-generated method stub
        String url = ((JuHeBean.ResultBean.DataBean) mlist.get(position)).getUrl();

        Intent intent = new Intent(mContext, NewDetaiActivity.class);
        intent.putExtra("url", url);
        mSpUtils.PutBoolean(((JuHeBean.ResultBean.DataBean) mlist.get(position)).getUrl(), true);
        mContext.startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        initData();

    }

    public void startDialog() {
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setTitle("提示");
        mProgressDialog.setMessage("正在检查网络.....");
        mProgressDialog.show();
    }

    public void stopDialog() {
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                if (mProgressDialog != null) {
                    mProgressDialog.cancel();
                }
            }
        }.start();
    }

    @Override
    public void initData() {
        connectionReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ConnectivityManager connectMgr = (ConnectivityManager) mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);
                NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


                if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
                    // unconnect network 这时提示用户网络断开信息
                    handler.sendEmptyMessage(FAULT);
                } else if (!mobNetInfo.isConnected() && wifiNetInfo.isConnected()) {
                    Log.i("lei", "wifi");
                    startDialog();
                    new Thread() {
                        @Override
                        public void run() {
                            boolean isUseFul = isNetworkOnline();
                            if (isUseFul) {
                                handler.sendEmptyMessage(SUECCESS);
                                stopDialog();
                            } else {
                                handler.sendEmptyMessage(FAULT_USEFUL);
                                stopDialog();
                            }
                        }

                    }.start();
                } else {
                    // connect network   可以做一些网络请求，刷新界面
                    handler.sendEmptyMessage(SUECCESS);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mContext.registerReceiver(connectionReceiver, intentFilter);
    }

    /**
     * 加载数据
     */


    class adapter extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if (mlist != null) {
                return mlist.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            if (mlist != null) {
                return mlist.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View view = null;
            if (view == null) {
                view = View.inflate(mContext, R.layout.item_news, null);
            } else {
                view = convertView;
            }

            TextView title = (TextView) view.findViewById(R.id.news_title);
            TextView from = (TextView) view.findViewById(R.id.news_from);
            LinearLayout height = (LinearLayout) view.findViewById(R.id.news_height);
            SimpleDraweeView iv = (SimpleDraweeView) view.findViewById(R.id.new_icon);
            SimpleDraweeView iv2 = (SimpleDraweeView) view.findViewById(R.id.new_icon1);
            SimpleDraweeView iv3 = (SimpleDraweeView) view.findViewById(R.id.new_icon2);


            title.setText(((JuHeBean.ResultBean.DataBean) mlist.get(position)).getTitle());
            from.setText(((JuHeBean.ResultBean.DataBean) mlist.get(position)).getAuthor_name());
            String thumbnail_pic_s = ((JuHeBean.ResultBean.DataBean) mlist.get(position))
                    .getThumbnail_pic_s();
            String thumbnail_pic_s2 = ((JuHeBean.ResultBean.DataBean) mlist.get(position))
                    .getThumbnail_pic_s02();
            String thumbnail_pic_s3 = ((JuHeBean.ResultBean.DataBean) mlist.get(position))
                    .getThumbnail_pic_s03();

            android.view.ViewGroup.LayoutParams params = height
                    .getLayoutParams();

            if (thumbnail_pic_s != null && thumbnail_pic_s2 != null
                    && thumbnail_pic_s3 != null) {
                iv.setImageURI(Uri.parse(thumbnail_pic_s));
                iv2.setImageURI(Uri.parse(thumbnail_pic_s2));
                iv3.setImageURI(Uri.parse(thumbnail_pic_s3));
            } else if (thumbnail_pic_s != null && thumbnail_pic_s2 == null
                    && thumbnail_pic_s3 == null) {
                iv.setImageURI(Uri.parse(thumbnail_pic_s));
                params.height = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 180, mContext.getResources()
                                .getDisplayMetrics());
                iv2.setVisibility(View.GONE);
                iv3.setVisibility(View.GONE);
            } else if (thumbnail_pic_s != null && thumbnail_pic_s2 != null
                    && thumbnail_pic_s3 == null) {
                iv.setImageURI(Uri.parse(thumbnail_pic_s));
                iv2.setImageURI(Uri.parse(thumbnail_pic_s2));

                iv3.setVisibility(View.GONE);
                params.height = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 100, mContext.getResources()
                                .getDisplayMetrics());
            } else {
                iv.setVisibility(View.GONE);
                iv2.setVisibility(View.GONE);
                iv3.setVisibility(View.GONE);
            }

            return view;
        }


    }


    @Override
    public void onResume() {

        super.onResume();
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


    @Override
    public void onDestroy() {
        if (connectionReceiver != null) {
            mContext.unregisterReceiver(connectionReceiver);
        }
        super.onDestroy();
    }

    class MyNetListener implements MyListener {

        @Override
        public void onSuccess(int i, Response response) {
            if (response.getHeaders().getResponseCode() == 200) {
                switch (i) {
                    case 1:
                        String result = (String) response.get();
                        if (result != null) {
                            mSpUtils.PutString(url, result);
                            prograssDate(result);
                            break;
                        } else {
                            Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_LONG).show();
                            return;
                        }
                }

            } else {
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    private void prograssDate(String json) {
        // TODO Auto-generated method stub
        Gson gson = new Gson();
        JuHeBean fromJson = gson.fromJson(json, JuHeBean.class);
        if (fromJson.getResult() != null) {
            fauild.setVisibility(View.GONE);
            find_listview.setVisibility(View.VISIBLE);
            List<JuHeBean.ResultBean.DataBean> data = fromJson.getResult().getData();
            mlist.addAll(data);
            mAdapter = new adapter();
            find_listview.setAdapter(mAdapter);
        } else {
            fauild.setVisibility(View.VISIBLE);
            find_listview.setVisibility(View.GONE);
            Toast.makeText(mContext, "服务器异常", Toast.LENGTH_LONG).show();
        }

    }
}
