package com.example.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by leizhongwei on 2018/3/21.
 */

public class MyPullableListView extends ListView {
    public MyPullableListView(Context context) {
        super(context);
    }

    public MyPullableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (getChildAt(0) != null) {
            if (getFirstVisiblePosition() == 0 && getChildAt(0).getTop() == 0) {//到头部了
                getParent().requestDisallowInterceptTouchEvent(false);//放行触摸
            } else {//没有到头部
                getParent().requestDisallowInterceptTouchEvent(true);//拦截触摸
            }
        }

        return super.onInterceptTouchEvent(ev);
    }

}
