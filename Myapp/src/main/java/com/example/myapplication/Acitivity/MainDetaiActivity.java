package com.example.myapplication.Acitivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.Answer;
import com.example.myapplication.db.Bill;
import com.example.myapplication.db.DbTools;
import com.example.myapplication.db.Goods;
import com.example.myapplication.db.Message;
import com.example.myapplication.db.Remark;
import com.example.myapplication.db.Sells;
import com.example.myapplication.view.adderView;
import com.lidroid.xutils.BitmapUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class MainDetaiActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private DbTools dbTools;
    private List<Goods> goodsList;
    private TextView detail_tv_title;
    private TextView tv_price;
    private TextView detail_tv_product;
    private ExpandableListView upload_list_item;
    private View header;
    private TextView btn_chat;
    private TextView btn_remark;
    private View maindetail_sell;
    private RelativeLayout activity_remark;
    private PopupWindow mPopBottom;
    private ArrayList list;
    private View remark_send;
    private ViewPager detail_simpleView;
    private BitmapUtils bitmapUtils;
    private myUploadAdapter adapter;
    private EditText et_remark;
    private List<Remark> remarkList;
    private TextView detail_mount;
    private String[] split;
    private TextView upload_header_loaction;
    private ImageView upload_header_call;
    private boolean isVisible = false;
    private TextView btn_buy;
    private RelativeLayout activity_buy;
    private SharedPreferences sp;
    private List<Message> queryUser;
    private int position;
    private TextView remark_answer;
    private List<Answer> answerList;
    private TextView upload_answer_number;
    private String price;
    private TextView detail_soid;
    private ImageView detail_iv;
    private SimpleDateFormat format;
//    private CustomStatusView as_status;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbTools = DbTools.getInstance(this);
        setContentView(R.layout.activity_main_detai);
        format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        sp = getSharedPreferences("text", Context.MODE_PRIVATE);
        maindetail_sell = findViewById(R.id.maindetail_sell);
        tv_price = (TextView) findViewById(R.id.tv_price);
        btn_chat = (TextView) findViewById(R.id.btn_chat);
        upload_list_item = (ExpandableListView) findViewById(R.id.upload_list_item);

        btn_buy = (TextView) findViewById(R.id.btn_buy);
        btn_remark = (TextView) findViewById(R.id.btn_remark);
        bitmapUtils = new BitmapUtils(this);
        header = View.inflate(this, R.layout.maindetail_head, null);

        initheadview();
        upload_list_item.addHeaderView(header);
        iv_back.setOnClickListener(this);
        btn_chat.setOnClickListener(this);
        btn_remark.setOnClickListener(this);
        btn_buy.setOnClickListener(this);

        int id = getIntent().getIntExtra("id", -1);
        remarkList = dbTools.QueryMarkListFromPosotion(id);
        Collections.reverse(remarkList);
        answerList = dbTools.QueryAnswerList();
        adapter = new MainDetaiActivity.myUploadAdapter(remarkList);
        upload_list_item.setAdapter(adapter);

    }

    /**
     * 自定义一个dialog
     *
     * @param i
     */
    private void initDialog(int i) {
        builder = new AlertDialog.Builder(MainDetaiActivity.this);
        View inflate = View.inflate(this, R.layout.layout_buy, null);
//        as_status = (CustomStatusView) inflate.findViewById(R.id.as_status);
        TextView as_tv = (TextView) inflate.findViewById(R.id.as_tv);

        builder.setView(inflate);
        dialog = builder.show();

//        if (i == 0) { //支付失败
//            as_status.loadFailure();
//            as_tv.setText("支付失败");
//            sleep(2000);
//        } else {   //支付成功
//            as_status.loadSuccess();
//            as_tv.setText("支付成功");
//            sleep(2000);
//        }


    }

    public void sleep(final int ms) {
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(ms);
                dialog.dismiss();
            }
        }.start();
    }

    private void initheadview() {
        detail_tv_product = (TextView) header.findViewById(R.id.detail_tv_product);
        detail_tv_title = (TextView) header.findViewById(R.id.detail_tv_title);
        detail_soid = (TextView) header.findViewById(R.id.detail_soid);

        iv_back = (ImageView) header.findViewById(R.id.iv_back);
        detail_simpleView = (ViewPager) header.findViewById(R.id.detail_simpleView);

        detail_iv = (ImageView) header.findViewById(R.id.detail_iv);
        detail_mount = (TextView) header.findViewById(R.id.detail_mount);
        upload_header_loaction = (TextView) header.findViewById(R.id.upload_header_loaction);
        upload_header_call = (ImageView) header.findViewById(R.id.upload_header_call);

        detail_tv_title = (TextView) header.findViewById(R.id.detail_tv_title);
    }


    /**
     * 加载所需的图片
     */
    private void initImage(int postion, String img, List<Goods> goodsList) {

        if (img != null) {
            split = img.split("&&&");
            //设置适配器
            detail_simpleView.setAdapter(new myPageAdapte(split));
            detail_simpleView.setOnPageChangeListener(new mypageListener(split));
        } else {
            detail_simpleView.setVisibility(View.GONE);
            detail_iv.setVisibility(View.VISIBLE);

            int id = goodsList.get(postion).getId();

            switch (id) {
                case 1: //键盘
                    detail_iv.setImageResource(R.mipmap.jixie);
                    break;  //鼠标
                case 2:
                    detail_iv.setImageResource(R.mipmap.shubiao);
                    break;
                case 3:    //耳机
                    detail_iv.setImageResource(R.mipmap.ear);
                    break;
            }

        }

    }


    class mypageListener implements ViewPager.OnPageChangeListener {

        private String[] split;

        public mypageListener(String[] split) {
            this.split = split;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            String txt = (position + 1) + "/" + split.length;
            detail_mount.setText(txt);
        }

        @Override
        public void onPageSelected(int position) {
        }


        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }


    class myPageAdapte extends PagerAdapter {

        private String[] list;

        public myPageAdapte(String[] mlist) {
            this.list = mlist;
        }

        @Override
        public int getCount() {
            if (list != null) {
                return list.length;
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {

            ImageView iv = new ImageView(MainDetaiActivity.this);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainDetaiActivity.this, DetailImageActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("split", list);

                    startActivity(intent);
                }
            });
            //date
            bitmapUtils.display(iv, list[position]);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }


    class myUploadAdapter extends BaseExpandableListAdapter {
        private List<Remark> remarks;

        public myUploadAdapter(List<Remark> remarks) {
            this.remarks = remarks;
        }

        @Override
        public int getGroupCount() {
            if (remarks != null) {
                return remarks.size();
            }
            return 0;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            if (dbTools.QueryAnswerPostionList(remarkList.get(groupPosition).getId()) != null) {
                return dbTools.QueryAnswerPostionList(remarkList.get(groupPosition).getId()).size();
            }
            return 0;
        }

        @Override
        public Object getGroup(int i) {
            return remarks.get(i);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return dbTools.QueryAnswerPostionList(remarkList.get(groupPosition).getId());
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
            View inflate = null;
            if (inflate == null) {
                inflate = View.inflate(MainDetaiActivity.this, R.layout.upload_list_item, null);
            } else {
                inflate = view;
            }

            TextView item_date = (TextView) inflate.findViewById(R.id.item_date);
            TextView item_remark = (TextView) inflate.findViewById(R.id.item_remark);
            TextView item_username = (TextView) inflate.findViewById(R.id.item_username);
            TextView upload_answer = (TextView) inflate.findViewById(R.id.upload_answer);
            upload_answer_number = (TextView) inflate.findViewById(R.id.upload_answer_number);

            if (getChildrenCount(i) != 0) {
                upload_answer_number.setText(getChildrenCount(i) + "");
            } else {
                upload_answer_number.setVisibility(View.GONE);
            }
            //date
            String date = remarks.get(i).getDate();
            String username = remarks.get(i).getUsername();
            final String substring = username.substring(7);
            String remark = remarks.get(i).getRemark();
            item_date.setText(date);
            item_remark.setText(remark);
            item_username.setText("**" + substring);
            upload_answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AnserwershowTextPopupWindow("答复//" + substring, remarkList.get(i).getId());
                }
            });
            return inflate;
        }


        @Override
        public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
            View v = null;
            if (v == null) {
                v = View.inflate(MainDetaiActivity.this, R.layout.answer_item, null);
            } else {
                v = view;
            }
            TextView main_username = (TextView) v.findViewById(R.id.main_username);
            TextView main_remark = (TextView) v.findViewById(R.id.main_remark);
            TextView main_answer = (TextView) v.findViewById(R.id.main_answer);

            main_username.setText(dbTools.QueryAnswerPostionList(remarkList.get(i).getId()).get(i1).getName());
            main_remark.setText(dbTools.QueryAnswerPostionList(remarkList.get(i).getId()).get(i1).getAnswer());

            if (dbTools.QueryAnswerPostionList(remarkList.get(i).getId()).get(i1).getName().trim().equals(remarks.get(i).getUsername().substring(7).trim()) == false) {
                main_answer.setText("回复 :");
            } else {
                main_answer.setText(":");
            }

            LinearLayout item_lineaLayout = v.findViewById(R.id.item_lineaLayout);
            item_lineaLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AnserwershowTextPopupWindow("回复//" + dbTools.QueryAnswerPostionList(remarkList.get(i).getId()).get(i1).getName(), remarkList.get(i).getId());
                }
            });
            return v;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }

    //展示数据数据

    private void initDate(int position) {
//        if (goodsList != null && position != -1) {
//            String title = goodsList.get(position).getTitle();
//            String Msg = goodsList.get(position).getMsg();
//            String sellnum = goodsList.get(position).getSellnum();
//            price = goodsList.get(position).getPrice();
//            String img = goodsList.get(position).getImg();
//            String location = goodsList.get(position).getLocation();
//            final String phone = goodsList.get(position).getPhone();
//            detail_tv_title.setText(Msg);
//            detail_tv_product.setText(title);
//            tv_price.setText(price);
//            detail_soid.setText(sellnum);
//            upload_header_loaction.setText(location);
//
//            upload_header_call.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (phone != null) {
//                        diallPhone(phone);
//                    }
//                }
//            });
//            initImage(position, img, goodsList);
//
//
//        }


        if (goodsList != null) {
            String title = goodsList.get(position).getTitle();
            String Msg = goodsList.get(position).getMsg();
            String sellnum = goodsList.get(position).getSellnum();
            price = goodsList.get(position).getPrice();
            String img = goodsList.get(position).getImg();
            String location = goodsList.get(position).getLocation();
            final String phone = goodsList.get(position).getPhone();
            detail_tv_title.setText(Msg);
            detail_tv_product.setText(title);
            tv_price.setText(price);
            detail_soid.setText(sellnum);
            upload_header_loaction.setText(location);

            upload_header_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (phone != null) {
                        diallPhone(phone);
                    }
                }
            });
            initImage(position, img, goodsList);


        }
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void diallPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.btn_chat:
                boolean logis = isLogin();
                if (logis) {
                    Intent intent = new Intent(MainDetaiActivity.this, ChatActivity.class);
                    int id = getIntent().getIntExtra("id", -1);
                    intent.putExtra("id", id);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_remark:
                boolean login = isLogin();
                if (login) {
                    showTextPopupWindow("写评论");
                } else {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.activity_remark:
                if (mPopBottom != null) {
                    mPopBottom.dismiss();
                }
                break;
            case R.id.btn_buy:
                boolean loging = isLogin();
                if (loging) {
                    BuyPopupWindow2();
                } else {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.activity_buy:
                if (mPopBottom != null) {
                    mPopBottom.dismiss();
                }
                break;

            case R.id.remark_send:  //发布
                String remarks = et_remark.getText().toString().trim();
                if (TextUtils.isEmpty(remarks) == false) {
                    Remark remark = new Remark();
                    queryUser = dbTools.QueryUser();
                    if (queryUser != null && queryUser.size() > 0) {
                        int id = sp.getInt("id", 123456);
                        for (Message msg : queryUser) {
                            if (msg.getId() == id) {
                                String phoneNum = queryUser.get(id - 1).getPhoneNum();
                                remark.setUsername(phoneNum);
                                break;
                            } else {
                                remark.setUsername("**2222");
                            }
                        }
                    }
                    int id = getIntent().getIntExtra("id", -1);
                    remark.setPostion(id + "");
                    remark.setDiver("数码");
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
                    String format = sdf.format(date);
                    remark.setDate(format);
                    remark.setRemark(remarks);
                    dbTools.addRemark(remark);

                    int ids = getIntent().getIntExtra("id", -1);
                    remarkList = dbTools.QueryMarkListFromPosotion(ids);
                    Collections.reverse(remarkList);
                    mPopBottom.dismiss();
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "不能输入为空，请重新输入！", Toast.LENGTH_SHORT).show();
                }


                break;
        }

    }

    private boolean isLogin() {
        List<Message> list = dbTools.QueryUser();
        if (list != null) {
            int id = sp.getInt("id", 123456);
            for (Message msg : list) {
                int Myid = msg.getId();
                if (Myid == id) {
                    return true;
                }
            }

        }
        return false;
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


    /**
     * 弹出修改文字窗口
     */

    private void showTextPopupWindow(String hint) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            //有底部导航栏的手机 虚拟home键 如果加上下面一句 底部的控制栏看不清了
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        View inflate = View.inflate(this, R.layout.activity_remark, null);
        et_remark = inflate.findViewById(R.id.et_remark);
        et_remark.setHint(hint);
        activity_remark = inflate.findViewById(R.id.activity_remark);
        remark_send = inflate.findViewById(R.id.remark_send);
        mPopBottom = new PopupWindow(inflate);
        mPopBottom.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        mPopBottom.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        mPopBottom.setTouchable(true);
        mPopBottom.setFocusable(true);
        mPopBottom.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0000000000);
        mPopBottom.setBackgroundDrawable(dw);
//		 动画效果 从底部弹起
        mPopBottom.setAnimationStyle(R.style.Animations_GrowFromBottom);
        mPopBottom.showAtLocation(maindetail_sell, Gravity.BOTTOM, 0, 0);
        activity_remark.setOnClickListener(this);
        remark_send.setOnClickListener(this);
    }

    /**
     * 弹出购买文字窗口
     */
    private void BuyPopupWindow2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            //有底部导航栏的手机 虚拟home键 如果加上下面一句 底部的控制栏看不清了
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        View inflate = View.inflate(this, R.layout.activity_buy, null);
        activity_buy = inflate.findViewById(R.id.activity_buy);
        mPopBottom = new PopupWindow(inflate);
        mPopBottom.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        mPopBottom.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        mPopBottom.setTouchable(true);
        mPopBottom.setFocusable(true);
        mPopBottom.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0000000000);
        mPopBottom.setBackgroundDrawable(dw);
//		 动画效果 从底部弹起
        mPopBottom.setAnimationStyle(R.style.Animations_GrowFromBottom);
        mPopBottom.showAtLocation(maindetail_sell, Gravity.BOTTOM, 0, 0);
        activity_buy.setOnClickListener(this);

        final adderView adderview = (adderView) inflate.findViewById(R.id.adderview);
        final Button btn_buy = (Button) inflate.findViewById(R.id.btn_buy);
        final int prices = Integer.parseInt(price);

        btn_buy.setText("¥" + prices);
        adderview.setOnValueChangeListene(new adderView.OnValueChangeListener() {
            @Override
            public void onValueChange(int value) {
                btn_buy.setText("¥" + value * prices);
            }
        });


        //购买
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int values = adderview.getValue();
                String num = QueryLoginNum();
                if (num != null && values >= 1) {
                    int i = dbTools.UpdateUserMoney(num, values * prices + "");
                    if (i == 0) {
                        mPopBottom.dismiss();
                        initDialog(i);
                    } else {

                        int id = getIntent().getIntExtra("id", -1);
                        //
                        String numbers = dbTools.QueryeSellNum(id);
                        if (numbers != null) {
                            int numb = Integer.parseInt(numbers);
                            dbTools.UpdateSellNum((values + numb) + "", id);

                            Sells sells = new Sells();
                            sells.setBuyGoodsId(id + "");
                            sells.setNumber(values + "");
                            sells.setTime(format.format(new Date()));

                            int user = sp.getInt("id", 123456);
                            sells.setUsername(user + "");

                            String pic = dbTools.QueryePic(id);
                            String tit = dbTools.QueryeSellTitle(id);

                            if (pic != null) {
                                sells.setImg(pic);
                            } else {
                                sells.setImg(null);
                            }
                            sells.setTit(tit);
                            dbTools.addSells(sells);
                            initDialog(i);

                            Bill bill = new Bill();
                            bill.setMoney("-" + values * prices);
                            bill.setUsername(num);
                            dbTools.ADDUserBillMoney(bill);
                            mPopBottom.dismiss();

                            onResume();
                        }

                    }
                }

            }
        });
    }


    /**
     * 回复文字窗口
     */

    private void AnserwershowTextPopupWindow(String hint, final long i) {
        Log.i("lei", "AnserwershowTextPopupWindow");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            //有底部导航栏的手机 虚拟home键 如果加上下面一句 底部的控制栏看不清了
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        View inflate = View.inflate(this, R.layout.activity_answer, null);
        et_remark = inflate.findViewById(R.id.et_remark);
        et_remark.setHint(hint);
        activity_remark = inflate.findViewById(R.id.activity_remark);
        remark_answer = (TextView) inflate.findViewById(R.id.remark_answer);
        remark_answer.setText("答复");

        mPopBottom = new PopupWindow(inflate);
        mPopBottom.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        mPopBottom.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        mPopBottom.setTouchable(true);
        mPopBottom.setFocusable(true);
        mPopBottom.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0000000000);
        mPopBottom.setBackgroundDrawable(dw);
//		 动画效果 从底部弹起
        mPopBottom.setAnimationStyle(R.style.Animations_GrowFromBottom);
        mPopBottom.showAtLocation(maindetail_sell, Gravity.BOTTOM, 0, 0);
        activity_remark.setOnClickListener(this);
        remark_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = et_remark.getText().toString().trim();
                Answer an = new Answer();
                if (TextUtils.isEmpty(answer) == false) {
                    queryUser = dbTools.QueryUser();
                    if (queryUser != null && queryUser.size() > 0) {
                        int id = sp.getInt("id", 123456);
                        for (Message msg : queryUser) {
                            if (msg.getId() == id) {
                                String phoneNum = queryUser.get(id - 1).getPhoneNum();
                                an.setName(phoneNum.substring(7));
                                break;
                            } else {
                                an.setName("**2222");
                            }
                        }
                    }
                    an.setAnswer(answer);
                    an.setAnswerId(i + "");
                    dbTools.addAnswer(an);
                    answerList.add(an);

                    mPopBottom.dismiss();
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainDetaiActivity.this, "不能输入为空，请重新输入！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onResume() {
//        goodsList = dbTools.QueryGoodsList();
//        Collections.reverse(goodsList);
//        position = getIntent().getIntExtra("position", -1);
//        initDate(position);
        int id = getIntent().getIntExtra("id", -1);
        goodsList = dbTools.QueryGoodsList(id + "");
        if (goodsList != null && goodsList.size() > 0) {
            initDate(0);
        } else {
            finish();
            Toast.makeText(MainDetaiActivity.this, "该商品已下架", Toast.LENGTH_SHORT).show();
        }

        super.onResume();
    }
}
