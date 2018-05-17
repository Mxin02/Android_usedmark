package com.example.myapplication.Listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by L on 2018/2/25.
 */

public abstract class PhoneEditChangedListener implements TextWatcher {
    int lastContentLength = 0;
    boolean isDelete = false;

    private String number;
    private EditText et;

    public PhoneEditChangedListener(String number, EditText et) {
        this.number = number;
        this.et = et;
    }


    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        StringBuffer sb = new StringBuffer(s);
        //是否为输入状态
        isDelete = s.length() > lastContentLength ? false : true;

        //输入是第4，第9位，这时需要插入空格
        if (!isDelete && (s.length() == 4 || s.length() == 9)) {
            if (s.length() == 4) {
                sb.insert(3, " ");
            } else {
                sb.insert(8, " ");
            }
            setContent(sb);
        }

        //删除的位置到4，9时，剔除空格
        if (isDelete && (s.length() == 4 || s.length() == 9)) {
            sb.deleteCharAt(sb.length() - 1);
            setContent(sb);
        }

        lastContentLength = sb.length();
    }
    /**
     * 添加或删除空格EditText的设置
     *
     * @param sb
     */
    private void setContent(StringBuffer sb) {
        et.setText(sb.toString());
        //移动光标到最后面
        et.setSelection(sb.length());
    }

}
