package com.example.myapplication.Acitivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.example.myapplication.R;
import com.example.myapplication.base.MyAdapter;
import com.example.myapplication.db.Bill;
import com.example.myapplication.db.DbTools;
import com.example.myapplication.db.Goods;
import com.example.myapplication.db.Message;
import com.example.myapplication.db.Sells;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;


public class BuyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private DbTools dbTools;
    private mAdapter adapter;
    private BitmapUtils bitmapUtils;
    private TextView buy_text;
    private ListView buy_list;
    private SharedPreferences sp;
    private List<Sells> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy2);
        sp = getSharedPreferences("text", Context.MODE_PRIVATE);
        dbTools = DbTools.getInstance(this);
        bitmapUtils = new BitmapUtils(this);
        buy_list = (ListView) findViewById(R.id.buy_list);
        buy_text = (TextView) findViewById(R.id.buy_text);

        adapter = new mAdapter();
        buy_list.setAdapter(adapter);
        buy_list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(this, MainDetaiActivity.class);
        int id = Integer.parseInt(list.get(position).getBuyGoodsId());
        intent.putExtra("id", id);
        startActivity(intent);
    }

    /**
     * 退款弹窗
     *
     * @param buyGoodsId
     */
    private void showNormalDialog(final String buyGoodsId, final int postion) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(BuyActivity.this);
        normalDialog.setTitle("退款提示");
        normalDialog.setMessage("亲亲，你确认要退款该商品吗？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (buyGoodsId != null) {
                            int GoodsId = Integer.parseInt(buyGoodsId);
                            int lang = dbTools.DeleteSellList(GoodsId, list.get(postion).getId());
                            if (lang != 0) {

                                List<Goods> goodses = dbTools.QueryGoodsList(buyGoodsId);

                                String price = goodses.get(0).getPrice();
                                //账单明细
                                Bill bill = new Bill();
                                bill.setUsername(QueryLoginNum());
                                int number = Integer.parseInt(list.get(postion).getNumber());
                                int prices = Integer.parseInt(price);

                                int toa = prices * number;
                                bill.setMoney(toa + "");
                                dbTools.ADDUserBillMoney(bill);
                                //余额
                                String initMoney = dbTools.QueryUserMoney(QueryLoginNum());
                                int IinitMoney = Integer.parseInt(initMoney);
                                dbTools.ADDUpdateUserMoney(QueryLoginNum(), String.valueOf(IinitMoney + toa));
                                //销售数量
                                int goid = Integer.parseInt(list.get(postion).getBuyGoodsId());
                                String numbers = dbTools.QueryeSellNum(goid);

                                if (numbers != null) {
                                    int num = Integer.parseInt(numbers);
                                    int i = num - number;
                                    if (i >= 0) {
                                        dbTools.UpdateSellNum(i + "", goid);
                                    }
                                }
                                onResume();
                                Toast.makeText(BuyActivity.this, "退款成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(BuyActivity.this, "退款失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 显示
        normalDialog.show();
    }

    class mAdapter extends MyAdapter {
        @Override
        public int getCount() {
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(BuyActivity.this).inflate(R.layout.sell_item, null);
            TextView main_msg = inflate.findViewById(R.id.main_msg);
            TextView main_num = inflate.findViewById(R.id.main_num);
            TextView main_time = inflate.findViewById(R.id.main_time);
            ImageView main_image = inflate.findViewById(R.id.main_image);
            TextView sell_back = inflate.findViewById(R.id.sell_back);

            sell_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showNormalDialog(list.get(i).getBuyGoodsId(), i);
                }
            });

            if (list != null) {
                main_msg.setText(list.get(i).getTit());
                String img = list.get(i).getImg();
                main_time.setText(list.get(i).getTime());
                main_num.setText("x" + list.get(i).getNumber());
                if (img != null) {
                    String[] split = img.split("&&&");
                    for (int b = 0; b < split.length; b++) {
                        if (split[0] != null) {
                            bitmapUtils.display(main_image, split[0]);
                        } else {
                            main_image.setImageResource(R.mipmap.xiaomi);
                        }
                    }
                } else {
                    main_msg.setText(list.get(i).getTit());
                    main_time.setText(list.get(i).getTime());
                    main_num.setText("x" + list.get(i).getNumber());
                    int id = Integer.parseInt(list.get(i).getBuyGoodsId());
                    if (id == 1) {
                        main_image.setImageResource(R.mipmap.jixie);
                    } else if (id == 2) {
                        main_image.setImageResource(R.mipmap.shubiao);
                    } else if (id == 3) {
                        main_image.setImageResource(R.mipmap.ear);
                    }
                }

            }
            return inflate;
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

    @Override
    public void onResume() {
        int user = sp.getInt("id", 123456);
        list = dbTools.QuerySells(user + "");

        if (list != null && list.size() > 0) {
            buy_list.setVisibility(View.VISIBLE);
            buy_text.setVisibility(View.GONE);

        } else {
            buy_list.setVisibility(View.GONE);
            buy_text.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
        super.onResume();
    }
}
