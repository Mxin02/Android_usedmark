package com.example.myapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.db.DbTools;
import com.example.myapplication.utils.SpUtils;

/**
 * Created by L on 2018/1/20.
 */

public abstract class baseFragment extends Fragment {
    public View mRootView;
    public Context mContext;
    private TextView title;
    public DbTools dbTools;
    public SpUtils mSpUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSpUtils = new SpUtils(getActivity());
        dbTools = DbTools.getInstance(getActivity());
        mContext = getActivity();
        mRootView = initContentView();
        initData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return mRootView;
    }

    public View initContentView() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.base_fragment, null);
        //标題
        title = (TextView) inflate.findViewById(R.id.base_fragment_title);

        //内容
        FrameLayout content = (FrameLayout) inflate.findViewById(R.id.base_fragment_content);
        //后退
        ImageView back = (ImageView) inflate.findViewById(R.id.base_fragment_back);

        setTitleView(title, back);

        content.addView(initView());
        return inflate;
    }

    public abstract View initView();


    //加载视图
    public void initData() {
    }

    //设置标题
    public abstract void setTitleView(TextView title, ImageView back);
}
