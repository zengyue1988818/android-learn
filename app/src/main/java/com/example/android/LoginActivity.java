package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity {
    //1.定义控件对象
    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cbAutoLogin;
    private Button bthLogin;

    private boolean isAutoLogin = false;

    /**
     * 1、将Activity类与xml布局进行关联：setContentView
     * 2、xml界面初始化
     * 3、设置按钮的监听
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //1、将Activity类与xml布局进行关联
        setContentView(R.layout.active_login);

        //2.初始化对象
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        bthLogin = findViewById(R.id.bt_log);

        //3.设置按钮的监听
        bthLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        //3.1 获取用户名和密码的值
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(this,"用户名或密码都不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        //3.2 比较用户和密码是否正确
        if("android".equals(username) && "123456".equals(password)){
            Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this,InfoActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
        }else {
            Toast.makeText(this,"用户名或密码不正确",Toast.LENGTH_LONG).show();
        }

    }

}