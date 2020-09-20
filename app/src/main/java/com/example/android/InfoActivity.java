package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvUserName;
    private EditText etRealName;
    private RadioGroup sexGroup;
    private CheckBox cbJava, cbAndroid, cbDatabase;
    private RadioButton rbMale, rbFemale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvUserName = findViewById(R.id.username);
        etRealName = findViewById(R.id.et_realName);
        sexGroup =  findViewById(R.id.group_sex);
        cbJava =  findViewById(R.id.cd_java);
        cbAndroid =  findViewById(R.id.cd_android);
        cbDatabase =  findViewById(R.id.cd_database);

        rbMale =  findViewById(R.id.rbtn_male);
        rbFemale =  findViewById(R.id.rbtn_female);

        //获取登录界面传递的数据
        Intent intent = getIntent();
        if (intent != null){
            String name = intent.getStringExtra("username");
            tvUserName.setText(name);
        }

        //设置点击事件，键盘事件的监听
        Button btnConfirm =  findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(this);

        //键盘事件处理
        etRealName.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode , KeyEvent event) {
                //按回车键隐藏键盘
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP){
                    //关闭键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null && imm.isActive()){
                        imm.hideSoftInputFromWindow((v.getApplicationWindowToken()),0);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    //处理点击的逻辑
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_confirm){
            getInfo();
        }
    }

    private void getInfo() {
        //获取控件的值
        String username = tvUserName.getText().toString().trim();
        String realName = etRealName.getText().toString().trim();
        String sex = "男";
        String favorite = "";
        int id = sexGroup.getCheckedRadioButtonId();
        if (id == R.id.rbtn_male){
            sex = "男";
        }else {
            sex = "女";
        }
        if(cbJava.isChecked()){
            favorite += cbJava.getText().toString() + ",";
        }
        if(cbAndroid.isChecked()){
            favorite += cbAndroid.getText().toString() + ",";
        }
        if(cbDatabase.isChecked()){
            favorite += cbDatabase.getText().toString() + ",";
        }

        //显示或传递内容
        String info = "用户名：" + username + "\n姓名："
                + realName + "\n性别："
                + sex + "\n喜欢的课程"
                + favorite.trim().substring(0, favorite.trim().length() - 1);
        Toast.makeText(InfoActivity.this,info,Toast.LENGTH_LONG).show();

        //跳转到主页面
        Intent intent = new Intent(InfoActivity.this,MainActivity.class);
        startActivity(intent);
    }
}