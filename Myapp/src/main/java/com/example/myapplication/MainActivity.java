package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.db.DbTools;
import com.example.myapplication.db.Goods;
import com.example.myapplication.fragment.FindFragment;
import com.example.myapplication.fragment.MainFragment;
import com.example.myapplication.fragment.MineFragment;
import com.example.myapplication.fragment.UploadFragment;
import com.example.myapplication.utils.PermisionUtils;
import com.example.myapplication.utils.SpUtils;


public class MainActivity extends AppCompatActivity  implements ActivityCompat.OnRequestPermissionsResultCallback{
    private static final int REQUEST_READ_PHONE_STATE = 1;
    //fragment
    private Class[] fragments = new Class[]{MainFragment.class, UploadFragment.class, FindFragment.class, MineFragment.class};
    private String[] tableTitle = {"首页", "发布", "发现", "我"};
    private int[] imageSeletor = {R.drawable.ic_home_tab_index_selector, R.drawable.ic_sort_tab_index_selector, R.drawable.ic_find_tab_index_selector, R.drawable.ic_me_tab_index_selector};
    private FragmentTabHost mTabHost;
    private DbTools dbTools;
    private SpUtils mSpUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            //TODO
        }

        mSpUtils = new SpUtils(this);
        dbTools = DbTools.getInstance(this);
        PermisionUtils.verifyStoragePermissions(MainActivity.this);
        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(R.id.fragmentTabhost);
        mTabHost.setup(MainActivity.this, getSupportFragmentManager(), android.R.id.tabcontent);

        for (int i = 0; i < fragments.length; i++) {
            View inflate = getLayoutInflater().inflate(R.layout.table_item, null);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.tab_iv);
            TextView textView = (TextView) inflate.findViewById(R.id.tab_tv);
            imageView.setBackgroundResource(imageSeletor[i]);
            textView.setText(tableTitle[i]);
            mTabHost.addTab(mTabHost.newTabSpec(tableTitle[i]).setIndicator(inflate), fragments[i]
                    , null);
        }
        Boolean isFirst = mSpUtils.getBoolean("isFirst", true);
        if (isFirst) {
            //预制数据
            initDate();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                }
                break;

            default:
                break;
        }
    }

    private void initDate() {
        //机械键盘
        Goods goods = new Goods();
        goods.setTitle("键盘");
        goods.setMsg("优牧马人机械键盘ek812黑轴青轴茶红轴104键电脑吃鸡游戏电竟");
        goods.setPrice("84");
        goods.setPhone("13890747854");
        goods.setLocation("数码产品");
        goods.setSellnum("0");
        goods.setImg(null);
        goods.setUsername(null);
        dbTools.addGoods(goods);


        //鼠标
        Goods goods2 = new Goods();
        goods2.setTitle("鼠标");
        goods2.setMsg("小米便携无线鼠标苹果鼠标笔记本电脑无线鼠标办公蓝牙游戏鼠标");
        goods2.setPrice("32");
        goods2.setPhone("15698896835");
        goods2.setLocation("数码产品");
        goods2.setSellnum("0");
        goods2.setImg(null);
        goods2.setUsername(null);
        dbTools.addGoods(goods2);

        //头戴游戏耳机
        Goods goods3 = new Goods();
        goods3.setTitle("头戴游戏耳机");
        goods3.setMsg("电脑耳机头戴式台式电竞游戏耳麦网吧带麦话筒");
        goods3.setPrice("29");
        goods3.setPhone("181343535353");
        goods3.setLocation("数码产品");
        goods3.setSellnum("0");
        goods3.setImg(null);
        goods3.setUsername(null);
        dbTools.addGoods(goods3);
        mSpUtils.PutBoolean("isFirst", false);

    }

}
