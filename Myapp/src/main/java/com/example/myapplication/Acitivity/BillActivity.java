package com.example.myapplication.Acitivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.db.DbTools;
import com.example.myapplication.db.Message;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class BillActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private DbTools dbTools;
    private ArrayAdapter adapter;
    private TextView bill_text;
    private ListView bill_list;
    private List mlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        dbTools = DbTools.getInstance(this);
        sp = getSharedPreferences("text", Context.MODE_PRIVATE);
        bill_list = (ListView) findViewById(R.id.bill_list);
        bill_text = (TextView) findViewById(R.id.bill_text);
        initDate();
        //为适配器添加数据源
        if (adapter != null) {
            bill_list.setAdapter(adapter);
        }

    }

    private String QueryLoginNum() {
        String phoneNum = null;
        List<Message> list = dbTools.QueryUser();
        if (list != null) {
            int id = sp.getInt("id", 123456);
            for (Message msg : list) {
                int Myid = msg.getId();
                if (Myid == id) {
                    phoneNum = msg.getPhoneNum();
                    return phoneNum;
                }
            }

        }
        return phoneNum;
    }

    public void initDate() {
        String user = QueryLoginNum();
        mlist = dbTools.QueryUserBillMoney(user + "");

        if (mlist != null && mlist.size() > 0) {
            bill_text.setVisibility(View.GONE);
            bill_list.setVisibility(View.VISIBLE);
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mlist);
            adapter.notifyDataSetChanged();
        } else {
            bill_text.setVisibility(View.VISIBLE);
            bill_list.setVisibility(View.GONE);
        }

        super.onResume();
    }
}
