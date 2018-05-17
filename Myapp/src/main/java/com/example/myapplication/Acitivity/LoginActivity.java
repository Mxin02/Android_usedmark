package com.example.myapplication.Acitivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Listener.PhoneEditChangedListener;
import com.example.myapplication.R;
import com.example.myapplication.base.Check;
import com.example.myapplication.db.DbTools;
import com.example.myapplication.db.Message;

import java.util.List;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private LinearLayout ll_login;
    private LinearLayout ll_quick_login;
    private View view_line_left;
    private View view_line_right;
    private TextView tv_count_register;
    private TextView tv_quick_register;
    private int ORANGE;
    private int GRAY;
    private ImageView iv_back;
    private DbTools dbTools;
    private SharedPreferences.Editor edit;
    private Button btn_get_code;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            int index = (int) msg.obj;
            if (index < 1) {
                btn_get_code.setText("获取验证码");
                btn_get_code.setBackgroundResource(R.color.check);
            } else {
                btn_get_code.setText(index + "s");
                btn_get_code.setBackgroundResource(R.color.login_unable_bg);
            }
        }
    };
    private EditText username;
    private EditText password;
    private EditText et_quick_phone;
    private EditText et_quick_code;
    private Button quick_login_btn;
    //判断在哪个界面
    private boolean isQuick = true;
    private CheckBox cb_show_pwd;
    private NotificationManager nManager;
    private Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbTools = DbTools.getInstance(this);
        nManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        isQuick = true;
        initColor();
        //back
        iv_back = (ImageView) findViewById(R.id.iv_back);
        //textview
        TextView register = (TextView) findViewById(R.id.tv_register);
        tv_count_register = (TextView) findViewById(R.id.tv_count_register);
        tv_quick_register = (TextView) findViewById(R.id.tv_quick_register);

        //Linealayout
        ll_login = (LinearLayout) findViewById(R.id.ll_login);
        ll_quick_login = (LinearLayout) findViewById(R.id.ll_quick_login);

        //view_line
        view_line_left = findViewById(R.id.view_line_left);
        view_line_right = findViewById(R.id.view_line_right);
        //验证码
        btn_get_code = (Button) findViewById(R.id.btn_get_code);

        //快速登录账户
        et_quick_phone = (EditText) findViewById(R.id.et_quick_phone);
        //快速登录验证码
        et_quick_code = (EditText) findViewById(R.id.et_quick_code);

        //用户名
        username = (EditText) findViewById(R.id.username);
        //密码
        password = (EditText) findViewById(R.id.password);

        //登录
        quick_login_btn = (Button) findViewById(R.id.quick_login_btn);

        //设置密码是否可见
        cb_show_pwd = (CheckBox) findViewById(R.id.cb_show_pwd);
        cb_show_pwd.setOnCheckedChangeListener(this);

        //editText手机号添加条件
        username.addTextChangedListener(new phontEdit(username.getText().toString().trim(), username));
        password.addTextChangedListener(new PwdEditChangedListener());

        et_quick_phone.addTextChangedListener(new quick_phontEdit(et_quick_phone.getText().toString().trim(), et_quick_phone));
        et_quick_code.addTextChangedListener(new CodeEditListener());

        register.setOnClickListener(this);
        tv_count_register.setOnClickListener(this);
        tv_quick_register.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        btn_get_code.setOnClickListener(this);

        if (btn_get_code.isEnabled()) {
            btn_get_code.setEnabled(false);
        }

        quick_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Message> list = dbTools.QueryUser();
                if (!isQuick) {   //账号
                    //账号登录
                    String user = username.getText().toString().trim();
                    String pass = password.getText().toString().trim();

                    if (user != null & pass != null) {
                        user = user.replaceAll(" ", "");
                        if (Check.isMobile(user.trim())) {
                            for (Message msg : list) {
                                if (msg.getPhoneNum().trim().equals(user) && msg.getPwd().trim().equals(pass)) {
                                    Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                                    edit.putInt("id", msg.getId());
                                    edit.commit();
                                    finish();
                                    return;
                                }
                            }
                            Toast.makeText(LoginActivity.this, "登录失败，请重新输入您的用户名或密码！", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Toast.makeText(LoginActivity.this, "请输入合法手机号码", Toast.LENGTH_SHORT).show();
                            password.setText("");
                            return;
                        }

                    }

                } else {  //手机快速登录
                    //快速登录
                    String quick_phone = et_quick_phone.getText().toString().trim();
                    String quick_code = et_quick_code.getText().toString().trim();
                    if (quick_phone != null && quick_code != null) {
                        quick_phone = quick_phone.replaceAll(" ", "");
                        if (Check.isMobile(quick_phone.trim())) {
                            for (final Message msg : list) {
                                if (msg.getPhoneNum().trim().equals(quick_phone)) {
                                    BmobSMS.verifySmsCode(LoginActivity.this, quick_phone, quick_code, new VerifySMSCodeListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if (e == null) {
                                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                                edit.putInt("id", msg.getId());
                                                edit.commit();
                                                finish();
                                                return;
                                            } else {
                                                Toast.makeText(LoginActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                    });
                                    return;
                                }
                            }
                            Toast.makeText(LoginActivity.this, "请先注册！", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Toast.makeText(LoginActivity.this, "请输入合法手机号码", Toast.LENGTH_SHORT).show();
                            et_quick_code.setText("");
                            return;
                        }
                    }
                }
            }
        });
    }

    protected void onResume() {
        et_quick_phone.setText("");
        et_quick_code.setText("");

        username.setText("");
        password.setText("");

        super.onResume();
    }

    /**
     * 加载颜色
     */

    private void initColor() {
        ORANGE = getResources().getColor(R.color.orange);
        GRAY = getResources().getColor(R.color.gray);

        SharedPreferences sp = getSharedPreferences("text", MODE_PRIVATE);
        edit = sp.edit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:  //注册
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.tv_count_register:  //账户登录

                isQuick = false;
                quick_login_btn.setEnabled(false);
                password.setText("");

                ll_login.setVisibility(View.VISIBLE);
                ll_quick_login.setVisibility(View.GONE);

                view_line_right.setVisibility(View.VISIBLE);
                view_line_left.setVisibility(View.INVISIBLE);

                tv_count_register.setTextColor(ORANGE);
                tv_quick_register.setTextColor(GRAY);


                break;

            case R.id.tv_quick_register:  //快速登录
                isQuick = true;
                ll_login.setVisibility(View.GONE);
                ll_quick_login.setVisibility(View.VISIBLE);

                view_line_right.setVisibility(View.INVISIBLE);
                view_line_left.setVisibility(View.VISIBLE);

                tv_count_register.setTextColor(GRAY);
                tv_quick_register.setTextColor(ORANGE);

                quick_login_btn.setEnabled(false);
                et_quick_code.setText("");
                break;
            case R.id.iv_back:  //返回
                finish();
                break;
            case R.id.btn_get_code:  //验证码
                if (btn_get_code.isEnabled()) {
                    btn_get_code.setEnabled(false);
                }
                String quick_phone = et_quick_phone.getText().toString().trim();
                if (quick_phone != null) {
                    quick_phone = quick_phone.replaceAll(" ", "");
                    if (Check.isMobile(quick_phone)) {
                        //请求发送短信

                        new CountDownTimer(60000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                btn_get_code.setText(millisUntilFinished / 1000 + "秒");
                                btn_get_code.setBackgroundResource(R.color.login_unable_bg);
                            }

                            @Override
                            public void onFinish() {
                                btn_get_code.setBackgroundResource(R.color.check);
                                btn_get_code.setText("获取验证码");
                                btn_get_code.setEnabled(true);
                            }
                        }.start();
                        //进行获取验证码操作和倒计时1分钟操作
                        BmobSMS.requestSMSCode(this, quick_phone, "短信模板", new RequestSMSCodeListener() {
                            @Override
                            public void done(Integer integer, BmobException e) {
                                if (e == null) {
                                    //发送成功时，让获取验证码按钮不可点击，且为灰色
                                    btn_get_code.setEnabled(false);
                                    Toast.makeText(LoginActivity.this, "验证码发送成功，请尽快使用", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(LoginActivity.this, "验证码发送失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(LoginActivity.this, "请输入合法手机号码", Toast.LENGTH_SHORT).show();
                        et_quick_code.setText("");
                        return;
                    }
                }
                break;

        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
//选择状态 显示明文--设置为可见的密码
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            password.setSelection(password.getText().length());
        } else {
//默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            password.setSelection(password.getText().length());
        }
    }


    class PwdEditChangedListener implements TextWatcher {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //手机号码
            String number = username.getText().toString().trim();
            //登录密码
            String pwd = password.getText().toString().trim();

            if (number.equals("") == false && pwd.equals("") == false) {
                quick_login_btn.setEnabled(true);
            } else {
                quick_login_btn.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //手机号码
            String number = username.getText().toString().trim();
            //登录密码
            String pwd = password.getText().toString().trim();

            if (number.equals("") == false && pwd.equals("") == false) {
                quick_login_btn.setEnabled(true);
            } else {
                quick_login_btn.setEnabled(false);
            }
        }

    }

    class CodeEditListener implements TextWatcher {

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //手机号码
            String phone = et_quick_phone.getText().toString().trim();
            //验证码
            String code = et_quick_code.getText().toString().trim();
            if (code.equals("") == false && phone.equals("") == false) {
                quick_login_btn.setEnabled(true);
            } else {
                quick_login_btn.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //手机号码
            String phone = et_quick_phone.getText().toString().trim();
            //验证码
            String code = et_quick_code.getText().toString().trim();
            if (code.equals("") == false && phone.equals("") == false) {
                quick_login_btn.setEnabled(true);
            } else {
                quick_login_btn.setEnabled(false);
            }
        }
    }

    class quick_phontEdit extends PhoneEditChangedListener {
        public quick_phontEdit(String number, EditText et) {
            super(number, et);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //手机号码
            String number = et_quick_phone.getText().toString().trim();
            //登录密码
            String pwd = et_quick_code.getText().toString().trim();
            boolean mobile = Check.isMobile(number.replaceAll(" ", "").trim());
            if (number.equals("") == false && mobile) {
                btn_get_code.setEnabled(true);
            } else {
                btn_get_code.setEnabled(false);
            }
            if (number.equals("") == false && pwd.equals("") == false) {
                quick_login_btn.setEnabled(true);
            } else {
                quick_login_btn.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //手机号码
            String number = et_quick_phone.getText().toString().trim();
            //登录密码
            String pwd = et_quick_code.getText().toString().trim();
            boolean mobile = Check.isMobile(number.replaceAll(" ", "").trim());
            if (number.equals("") == false && mobile) {
                btn_get_code.setEnabled(true);
            } else {
                btn_get_code.setEnabled(false);
            }
            if (number.equals("") == false && pwd.equals("") == false) {
                quick_login_btn.setEnabled(true);
            } else {
                quick_login_btn.setEnabled(false);
            }
        }
    }


    class phontEdit extends PhoneEditChangedListener {

        public phontEdit(String number, EditText et) {
            super(number, et);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //手机号码
            String number = username.getText().toString().trim();
            //登录密码
            String pwd = password.getText().toString().trim();

            if (number.equals("") == false && pwd.equals("") == false) {
                quick_login_btn.setEnabled(true);
            } else {
                quick_login_btn.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //手机号码
            String number = username.getText().toString().trim();
            //登录密码
            String pwd = password.getText().toString().trim();

            if (number.equals("") == false && pwd.equals("") == false) {
                quick_login_btn.setEnabled(true);
            } else {
                quick_login_btn.setEnabled(false);
            }
        }
    }
}



