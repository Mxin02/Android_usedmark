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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.myapplication.R;
import com.example.myapplication.db.DbTools;
import com.example.myapplication.db.Goods;
import com.lidroid.xutils.BitmapUtils;

import java.text.SimpleDateFormat;
import java.util.List;

public class UploadsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DbTools dbTools;
    private UploadsActivity.mAdapter adapter;
    private BitmapUtils bitmapUtils;
    private TextView buy_text;
    private ListView buy_list;
    private SharedPreferences sp;
    private List<Goods> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploads);
        sp = getSharedPreferences("text", Context.MODE_PRIVATE);
        dbTools = DbTools.getInstance(this);
        bitmapUtils = new BitmapUtils(this);
        buy_list = (ListView) findViewById(R.id.uplod_list);
        buy_text = (TextView) findViewById(R.id.uplod_text);
        adapter = new UploadsActivity.mAdapter();
        buy_list.setAdapter(adapter);
        buy_list.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(this, MainDetaiActivity.class);
        int id = list.get(position).getId();
        intent.putExtra("id", id);
        startActivity(intent);
    }

    class mAdapter extends BaseSwipeAdapter {
        @Override
        public int getCount() {
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public int getSwipeLayoutResourceId(int position) {
            return R.id.swipe;
        }

        @Override
        public View generateView(final int position, ViewGroup parent) {
            View inflate = LayoutInflater.from(UploadsActivity.this).inflate(R.layout.upload_item, null);
            final SwipeLayout swipeLayout = (SwipeLayout) inflate.findViewById(getSwipeLayoutResourceId(position));
            TextView uplod_lose = (TextView) inflate.findViewById(R.id.swipe_delete);
            TextView main_msg = inflate.findViewById(R.id.main_msg);
            TextView main_num = inflate.findViewById(R.id.main_num);
                     TextView textView = inflate.findViewById(R.id.textView);


            uplod_lose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = list.get(position).getId();
                    showNormalDialog(id, swipeLayout);
                }
            });

            main_num.setText(list.get(position).getSellnum());
            ImageView main_image = inflate.findViewById(R.id.main_image);
            if (list != null) {
                main_msg.setText(list.get(position).getTitle());
                String img = list.get(position).getImg();
                if (img != null) {
                    String[] split = img.split("&&&");
                    for (int b = 0; b < split.length; b++) {
                        if (split[0] != null) {
                            bitmapUtils.display(main_image, split[0]);
                        } else {
                            main_image.setImageResource(R.mipmap.xiaomi);
                        }
                    }
                }
            }

            swipeLayout.setSwipeEnabled(true);
            swipeLayout.setClickToClose(true);

            return inflate;
        }

        @Override
        public void fillValues(int position, View convertView) {

        }
    }


    private void showNormalDialog(final int id, final SwipeLayout swipeLayout) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(UploadsActivity.this);
        normalDialog.setTitle("提醒");
        normalDialog.setMessage("你确认要下架该商品吗？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //数据库中删除id的数据
                        int lang = dbTools.DeleteGoodsList(id);

                        if (lang != 0) {
                            onResume();
                            Toast.makeText(UploadsActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UploadsActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        swipeLayout.close();
                    }
                });
        // 显示
        normalDialog.show();
    }

    @Override
    public void onResume() {
        int user = sp.getInt("id", 123456);
        list = dbTools.QueryGoodsListForUser(user + "");
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
