package com.example.myapplication.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Acitivity.BillActivity;
import com.example.myapplication.Acitivity.BuyActivity;
import com.example.myapplication.Acitivity.LoginActivity;
import com.example.myapplication.Acitivity.UploadsActivity;
import com.example.myapplication.R;
import com.example.myapplication.base.baseFragment;
import com.example.myapplication.db.Bill;
import com.example.myapplication.db.Message;

import java.util.List;

public class MineFragment extends baseFragment implements View.OnClickListener {

    private RelativeLayout rl_unlogin;
    private RelativeLayout rl_logined;
    private SharedPreferences.Editor edit;
    private SharedPreferences sp;
    private TextView tv_name;
    private Button quick_login_btn;
    private TextView tv_blance;
    private RelativeLayout rl_unpaied_order;
    private RelativeLayout rl_paied_order;
    private LinearLayout mine_ll;
    private PopupWindow mPopBottom;
    private String phoneNum;
    private RelativeLayout rl_paied_order_upload;
    private RelativeLayout rl_generdisplay;

    @Override
    public View initView() {
        sp = getActivity().getSharedPreferences("text", Context.MODE_PRIVATE);

        View inflate = LayoutInflater.from(mContext).inflate(R.layout.fragment_mine, null);
        Button btn_login = (Button) inflate.findViewById(R.id.btn_login);
        //未登录
        rl_unlogin = (RelativeLayout) inflate.findViewById(R.id.rl_unlogin);
        //登录
        rl_logined = (RelativeLayout) inflate.findViewById(R.id.rl_logined);
        //名字
        tv_name = (TextView) inflate.findViewById(R.id.tv_name);
        //账户余额
        tv_blance = (TextView) inflate.findViewById(R.id.tv_blance);

        rl_unpaied_order = (RelativeLayout) inflate.findViewById(R.id.rl_unpaied_order);
        rl_paied_order = (RelativeLayout) inflate.findViewById(R.id.rl_paied_order);
        rl_generdisplay = (RelativeLayout) inflate.findViewById(R.id.rl_generdisplay);

        rl_paied_order_upload = (RelativeLayout) inflate.findViewById(R.id.rl_paied_order_upload);
        //退出登录
        quick_login_btn = (Button) inflate.findViewById(R.id.quick_login_btn);
        quick_login_btn.setVisibility(View.GONE);

        quick_login_btn.setOnClickListener(this);
        rl_logined.setOnClickListener(this);
        rl_unpaied_order.setOnClickListener(this);
        rl_paied_order.setOnClickListener(this);
        rl_paied_order_upload.setOnClickListener(this);
        rl_generdisplay.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        return inflate;
    }

    @Override
    public void setTitleView(TextView title, ImageView back) {
        title.setText("我的");
        back.setVisibility(View.GONE);
    }


    @Override
    public void onResume() {
        List<Message> list = dbTools.QueryUser();
        if (list != null) {
            int id = sp.getInt("id", 123456);
            for (Message msg : list) {
                if (msg.getId() == id) {
                    rl_logined.setVisibility(View.VISIBLE);
                    rl_unlogin.setVisibility(View.GONE);
                    quick_login_btn.setVisibility(View.VISIBLE);
                    phoneNum = msg.getPhoneNum();
                    tv_name.setText(phoneNum);
                    //从数据库中查询
                    String money = dbTools.QueryUserMoney(phoneNum);
                    if (money != null) {
                        tv_blance.setText(money);
                    } else {
                        tv_blance.setText("hahah");
                    }
                    break;
                } else {
                    rl_logined.setVisibility(View.GONE);
                    rl_unlogin.setVisibility(View.VISIBLE);
                    quick_login_btn.setVisibility(View.GONE);
                }
            }
        }
        super.onResume();

    }


    /**
     * 弹出修改文字窗口
     */
    private void showTextPopupWindow(String text, String hint) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            //有底部导航栏的手机 虚拟home键 如果加上下面一句 底部的控制栏看不清了
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        View inflate = View.inflate(getActivity(), R.layout.activity_add, null);
        final EditText et_remark2 = inflate.findViewById(R.id.et_remark2);
        et_remark2.setInputType(InputType.TYPE_CLASS_NUMBER);
        et_remark2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        View activity_remark = inflate.findViewById(R.id.activity_remark);
        TextView remark_send = (TextView) inflate.findViewById(R.id.remark_send);
        remark_send.setText(text);
        et_remark2.setHint(hint);

        et_remark2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                int len = s.toString().length();
                if (len > 1 && text.startsWith("0")) {
                    s.replace(0,1,"");
                }
            }
        });

        et_remark2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
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
        mPopBottom.showAtLocation(activity_remark, Gravity.BOTTOM, 0, 0);
        activity_remark.setOnClickListener(this);
        remark_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                String money = et_remark2.getText().toString();
                String initMoney = dbTools.QueryUserMoney(phoneNum);

                int Imoney = Integer.parseInt(money);
                if (Imoney > 0) {
                    int IinitMoney = Integer.parseInt(initMoney);
                    int i = dbTools.ADDUpdateUserMoney(phoneNum, (IinitMoney + Imoney) + "");


                    Bill bill = new Bill();

                    bill.setUsername(phoneNum);
                    bill.setMoney(money);
                    dbTools.ADDUserBillMoney(bill);
                    Toast.makeText(mContext, "恭喜您，成功充值了" + money + "元", Toast.LENGTH_SHORT).show();
                    mPopBottom.dismiss();

                    onResume();
                } else {
                    Toast.makeText(mContext, "最少充值金额是一元", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }




    private boolean islogin() {
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

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.activity_remark:
                if (mPopBottom != null) {
                    mPopBottom.dismiss();
                }
                break;
            case R.id.rl_unpaied_order:  //充值
                if (islogin()) {
                    showTextPopupWindow("充值", "请选择您要充值的金额");
                } else {
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.rl_paied_order:   //已付款

                if (islogin()) {
                    Intent intent = new Intent(mContext, BuyActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.rl_paied_order_upload:   //已发布的商品
                if (islogin()) {
                    Intent intent = new Intent(mContext, UploadsActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.rl_generdisplay:   //账单
                if (islogin()) {
                    Intent intent = new Intent(mContext, BillActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btn_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.rl_logined:

                break;
            case R.id.quick_login_btn:  //退出登录
                rl_logined.setVisibility(View.GONE);
                rl_unlogin.setVisibility(View.VISIBLE);
                quick_login_btn.setVisibility(View.GONE);
                sp.edit().putInt("id", 54321).commit();
                break;

        }

    }
}
