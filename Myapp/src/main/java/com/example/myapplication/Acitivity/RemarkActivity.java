package com.example.myapplication.Acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;

public class RemarkActivity extends AppCompatActivity {

    private EditText et_remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remark);
        et_remark = (EditText) findViewById(R.id.et_remark);
    }

    public void remark(View view) {
        String remark = et_remark.getText().toString().trim();
    }
}
