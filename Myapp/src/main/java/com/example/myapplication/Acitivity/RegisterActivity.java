package com.example.myapplication.Acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.Listener.PhoneEditChangedListener;
import com.example.myapplication.R;
import com.example.myapplication.base.Check;
import com.example.myapplication.db.DbTools;
import com.example.myapplication.db.Message;

import java.util.List;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    private EditText et_phone;
    private EditText et_pwd;
    private EditText et_repwd;
    private ImageView iv_back;
    private Button bt_get_check_code;
    private DbTools dbTools;
    private String number;
    private String pwd;
    private String repwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbTools = DbTools.getInstance(this);

        initView();

    }

    /**
     * 找到控件
     */
    private void initView() {

        //返回
        iv_back = (ImageView) findViewById(R.id.iv_back);
        //注册
        bt_get_check_code = (Button) findViewById(R.id.bt_get_check_code);

        //切换
        CheckBox cb_show_pwd = (CheckBox) findViewById(R.id.cb_show_pwd);
        cb_show_pwd.setOnCheckedChangeListener(this);

        //手机号码
        et_phone = (EditText) findViewById(R.id.et_phone);
        String num = et_phone.getText().toString().trim();
        et_phone.addTextChangedListener(new PhoneEditextends(num, et_phone));
        //登录密码
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_pwd.addTextChangedListener(new EditChangedListener());
        //确认登录密码
        et_repwd = (EditText) findViewById(R.id.et_repwd);
        et_repwd.addTextChangedListener(new EditChangedListener());

        iv_back.setOnClickListener(this);
        bt_get_check_code.setOnClickListener(this);
    }

    class PhoneEditextends extends PhoneEditChangedListener {

        public PhoneEditextends(String number, EditText et) {
            super(number, et);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //手机号码
            number = et_phone.getText().toString().trim();
            //登录密码
            pwd = et_pwd.getText().toString().trim();
            //确认登录密码
            repwd = et_repwd.getText().toString().trim();

            if (number.equals("") == false && pwd.equals("") == false) {
                bt_get_check_code.setEnabled(true);
            } else {
                bt_get_check_code.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //手机号码
            number = et_phone.getText().toString().trim();
            //登录密码
            pwd = et_pwd.getText().toString().trim();
            //确认登录密码
            repwd = et_repwd.getText().toString().trim();

            if (number.equals("") == false && pwd.equals("") == false && repwd.equals("") == false) {
                bt_get_check_code.setEnabled(true);
            } else {
                bt_get_check_code.setEnabled(false);
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if (b) {
//选择状态 显示明文--设置为可见的密码
            et_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            et_pwd.setSelection(et_pwd.getText().length());
        } else {
//默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
            et_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            et_pwd.setSelection(et_pwd.getText().length());
        }
    }


    class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //手机号码
            number = et_phone.getText().toString().trim();
            //登录密码
            pwd = et_pwd.getText().toString().trim();
            //确认登录密码
            repwd = et_repwd.getText().toString().trim();

            if (number.equals("") == false && pwd.equals("") == false) {
                bt_get_check_code.setEnabled(true);
            } else {
                bt_get_check_code.setEnabled(false);
            }
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            //手机号码
            number = et_phone.getText().toString().trim();
            //登录密码
            pwd = et_pwd.getText().toString().trim();
            //确认登录密码
            repwd = et_repwd.getText().toString().trim();

            if (number.equals("") == false && pwd.equals("") == false && repwd.equals("") == false) {
                bt_get_check_code.setEnabled(true);
            } else {
                bt_get_check_code.setEnabled(false);
            }
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_back:      //返回
                this.finish();
                break;
            case R.id.bt_get_check_code:   //注册

                //手机号码
                number = et_phone.getText().toString().trim();
                String num = number.replaceAll(" ", "");
                //登录密码
                pwd = et_pwd.getText().toString().trim();
                //确认登录密码
                repwd = et_repwd.getText().toString().trim();

                if (Check.isMobile(num)) {
                    if (pwd.equals(repwd)) {
                        //查询是否注册
                        List<Message> list = dbTools.QueryUser();

                        for (Message MSG : list) {
                            if (num.equals(MSG.getPhoneNum())) {
                                et_pwd.setText("");
                                et_repwd.setText("");
                                Toast.makeText(this, "该手机号已被注册！", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }

                        Message message = new Message();
                        message.setPhoneNum(num);
                        message.setPwd(repwd);
                        message.setMoney(10000000+"");
                        dbTools.addUser(message);
                        Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
                        this.finish();
                    } else {
                        et_pwd.setText("");
                        et_repwd.setText("");
                        Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入合法手机号码", Toast.LENGTH_SHORT).show();
                    et_pwd.setText("");
                    et_repwd.setText("");
                }

                break;
        }

    }

}

