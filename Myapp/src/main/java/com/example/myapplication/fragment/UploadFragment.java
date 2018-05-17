package com.example.myapplication.fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Acitivity.LoginActivity;
import com.example.myapplication.Acitivity.PlusImageActivity;
import com.example.myapplication.MainConstant;
import com.example.myapplication.db.Goods;
import com.example.myapplication.db.Message;
import com.example.myapplication.view.PictureSelectorConfig;
import com.example.myapplication.R;
import com.example.myapplication.adapter.GridViewAdapter;
import com.example.myapplication.base.baseFragment;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class UploadFragment extends baseFragment implements View.OnClickListener {
    private static final int SUECCESSS = 122;
    public static final int FAUSLT = 4243;
    private GridView gridView;
    private ArrayList<String> mPicList = new ArrayList<>(); //上传的图片凭证的数据源
    private GridViewAdapter mGridViewAddImgAdapter; //展示上传的图片的适配器
    private RelativeLayout rl_paied_order_price;
    private TextView upload_price;
    private TextView upload_diver;
    private Button quick_login_btn;
    private EditText upload_title;
    private EditText upload_content;
    private ImageView upload_add;
    private AlertDialog.Builder builder;
    private RelativeLayout rl_paied_order_phone;
    private RelativeLayout rl_paied_order_loactions;
    private PopupWindow mPopBottom;
    private LinearLayout activity_up_load_acitivity;
    private RelativeLayout activity_remark;
    private TextView upload_phone;
    private String price;
    private String phone;
    private TextView upload_location;
    private String locations;
    private SharedPreferences sp;
    private View inflate;
    private LinearLayout activity_up_load_acitivity_rela;
    private Button upload_login;


    @Override
    public View initView() {
        inflate = LayoutInflater.from(mContext).inflate(R.layout.activity_upload, null);
        sp = getActivity().getSharedPreferences("text", Context.MODE_PRIVATE);
        gridView = (GridView) inflate.findViewById(R.id.lod_ridView);
        rl_paied_order_price = (RelativeLayout) inflate.findViewById(R.id.rl_paied_order_price);
        rl_paied_order_loactions = (RelativeLayout) inflate.findViewById(R.id.rl_paied_order_loactions);
        rl_paied_order_phone = (RelativeLayout) inflate.findViewById(R.id.rl_paied_order_phone);
        activity_up_load_acitivity = (LinearLayout) inflate.findViewById(R.id.activity_up_load_acitivity);
        upload_login = (Button) inflate.findViewById(R.id.upload_login);

        activity_up_load_acitivity_rela = (LinearLayout) inflate.findViewById(R.id.activity_up_load_acitivity_rela);
        upload_price = (TextView) inflate.findViewById(R.id.upload_price);
        upload_location = (TextView) inflate.findViewById(R.id.upload_location);
        upload_add = (ImageView) inflate.findViewById(R.id.upload_add);
        upload_phone = (TextView) inflate.findViewById(R.id.upload_phone);

        upload_title = (EditText) inflate.findViewById(R.id.upload_title);
        upload_content = (EditText) inflate.findViewById(R.id.upload_content);

        quick_login_btn = (Button) inflate.findViewById(R.id.quick_login_btn);

        initGridView();
        upload_add.setOnClickListener(this);
        rl_paied_order_price.setOnClickListener(this);

        rl_paied_order_loactions.setOnClickListener(this);
        rl_paied_order_phone.setOnClickListener(this);
        quick_login_btn.setOnClickListener(this);
        upload_login.setOnClickListener(this);
        return inflate;
    }


    @Override
    public void onResume() {
        List<Message> list = dbTools.QueryUser();
        if (list != null && list.size() > 0) {
            int id = sp.getInt("id", 123456);
            for (Message msg : list) {
                if (msg.getId() == id) {
                    activity_up_load_acitivity.setVisibility(View.VISIBLE);
                    activity_up_load_acitivity_rela.setVisibility(View.GONE);
                    break;
                } else {
                    activity_up_load_acitivity.setVisibility(View.GONE);
                    activity_up_load_acitivity_rela.setVisibility(View.VISIBLE);
                }
            }
        } else {
            activity_up_load_acitivity.setVisibility(View.GONE);
            activity_up_load_acitivity_rela.setVisibility(View.VISIBLE);
        }
        super.onResume();
    }

    //初始化展示上传图片的GridView
    private void initGridView() {
        mGridViewAddImgAdapter = new GridViewAdapter(mContext, mPicList);
        gridView.setAdapter(mGridViewAddImgAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    //如果“增加按钮形状的”图片的位置是最后一张，且添加了的图片的数量不超过5张，才能点击
                    if (mPicList.size() == MainConstant.MAX_SELECT_PIC_NUM) {
                        //最多添加5张图片
                        viewPluImg(position);
                    } else {
                        //添加凭证图片
                        selectPic(MainConstant.MAX_SELECT_PIC_NUM - mPicList.size());
                    }
                } else {
                    viewPluImg(position);
                }
            }
        });
    }

    //查看大图
    private void viewPluImg(int position) {
        Intent intent = new Intent(mContext, PlusImageActivity.class);
        intent.putStringArrayListExtra(MainConstant.IMG_LIST, mPicList);
        intent.putExtra(MainConstant.POSITION, position);
        startActivityForResult(intent, MainConstant.REQUEST_CODE_MAIN);
    }


    /**
     * 打开相册或者照相机选择凭证图片，最多5张
     *
     * @param maxTotal 最多选择的图片的数量
     */
    private void selectPic(int maxTotal) {
        PictureSelectorConfig.initMultiConfig(this, maxTotal);
    }

    // 处理选择的照片的地址
    private void refreshAdapter(List<LocalMedia> picList) {
        for (LocalMedia localMedia : picList) {
            //被压缩后的图片路径
            if (localMedia.isCompressed()) {
                String compressPath = localMedia.getCompressPath(); //压缩后的图片路径
                mPicList.add(compressPath); //把图片添加到将要上传的图片数组//
                // 传递给activity
                mGridViewAddImgAdapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    refreshAdapter(PictureSelector.obtainMultipleResult(data));

                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
            }
        }
        if (requestCode == MainConstant.REQUEST_CODE_MAIN && resultCode == MainConstant.RESULT_CODE_VIEW_IMG) {
            //查看大图页面删除了图片
            ArrayList<String> toDeletePicList = data.getStringArrayListExtra(MainConstant.IMG_LIST); //要删除的图片的集合
            mPicList.clear();
            mPicList.addAll(toDeletePicList);
            mGridViewAddImgAdapter.notifyDataSetChanged();
        }
    }

    public void onCompelte() {
        upload_price.setText("开个价");
        upload_phone.setText("留个电话");
        upload_location.setText("分个类");
        upload_content.setText("");
        upload_title.setText("");
        mPicList.clear();
        upload_add.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.GONE);
        super.onResume();
    }

    @Override
    public void setTitleView(TextView title, ImageView back) {
        title.setText("发布");
        back.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {

            case R.id.upload_add: //添加图片
                selectPic(9);
                upload_add.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_paied_order_price: //价格
                showTextPopupWindow("确定", "请输入合适的价格", 3);
                break;

            case R.id.rl_paied_order_loactions: //位置

                showListDialog();
                break;
            case R.id.rl_paied_order_phone: //电话
                showTextPopupWindowPhone("确定", "请输入您的联系方式", 4);
                break;
            case R.id.upload_login: //登录
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
            case R.id.activity_remark:
                if (mPopBottom != null) {
                    mPopBottom.dismiss();
                }
                break;

            case R.id.quick_login_btn: //提交
                String title = upload_title.getText().toString().trim();
                String content = upload_content.getText().toString().trim();

                if (mPicList != null && mPicList.size() > 0 && title.equals("") == false && title != null && content.equals("") == false && content != null && phone != null && phone.equals("") == false && price != null && price.equals("") == false && TextUtils.isEmpty(locations) == false) {
                    //插入到数据库
                    if (dbTools != null) {
                        Goods goods = new Goods();
                        goods.setTitle(title);
                        goods.setMsg(content);
                        goods.setPrice(price);
                        goods.setPhone(phone);
                        goods.setLocation(locations);
                        goods.setSellnum("0");
                        //将图片插入到数据库
                        StringBuilder sb = new StringBuilder();
                        for (String pic : mPicList) {
                            sb.append(pic).append("&&&");
                        }
                        int user = sp.getInt("id", 123456);
                        goods.setUsername(user + "");
                        String ima = sb.toString().trim();
                        goods.setImg(ima);
                        dbTools.addGoods(goods);
                        phone = null;
                        title = null;
                        content = null;
                        price = null;
                        locations = null;
                        Toast.makeText(mContext, "发布成功！", Toast.LENGTH_LONG).show();
                        onCompelte();
                    }
                } else {
                    Toast.makeText(mContext, "请填写完整信息！", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    private void showListDialog() {
        final String[] items = {"电器", "数码产品", "运动健身", "校园代步","衣物伞帽", "图书教材", "其他"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(mContext);
        listDialog.setTitle("分类");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which 下标从0开始
                upload_location.setText(items[which]);
                locations = items[which];
            }
        });
        listDialog.show();
    }


    /**
     * 弹出修改文字窗口
     */
    private void showTextPopupWindow(String text, String hint, final int i) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            //有底部导航栏的手机 虚拟home键 如果加上下面一句 底部的控制栏看不清了
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        View inflate = View.inflate(getActivity(), R.layout.activity_remark, null);
        final EditText et_remark = inflate.findViewById(R.id.et_remark);
        et_remark.setInputType(InputType.TYPE_CLASS_NUMBER);
        et_remark.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        activity_remark = inflate.findViewById(R.id.activity_remark);
        TextView remark_send = (TextView) inflate.findViewById(R.id.remark_send);
        remark_send.setText(text);
        et_remark.setHint(hint);

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
                switch (i) {
                    case 3:  //价格
                        price = et_remark.getText().toString().trim();
                        if (price != null && price.equals("") == false) {
                            if (mPopBottom != null) {
                                mPopBottom.dismiss();
                            }
                            upload_price.setText(price);
                        } else {
                            Toast.makeText(mContext, "请输入价格！", Toast.LENGTH_LONG).show();
                        }

                        break;
                }
            }
        });
    }

    private void showTextPopupWindowPhone(String text, String hint, final int i) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            //有底部导航栏的手机 虚拟home键 如果加上下面一句 底部的控制栏看不清了
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        View inflate = View.inflate(getActivity(), R.layout.activity_remark, null);
        final EditText et_remark = inflate.findViewById(R.id.et_remark);
        et_remark.setInputType(InputType.TYPE_CLASS_NUMBER);
        et_remark.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        activity_remark = inflate.findViewById(R.id.activity_remark);
        TextView remark_send = (TextView) inflate.findViewById(R.id.remark_send);
        remark_send.setText(text);
        et_remark.setHint(hint);

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
                switch (i) {
                    case 4:   //电话
                        phone = et_remark.getText().toString().trim();
                        if (phone != null && phone.equals("") == false) {
                            if (mPopBottom != null) {
                                mPopBottom.dismiss();
                            }
                            upload_phone.setText(phone);
                        } else {
                            Toast.makeText(mContext, "请输入正确的电话！", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            }
        });
    }

}
