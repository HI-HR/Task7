package com.example.task7;

import static com.example.task7.Util.NetUtil.doPost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.task7.Home.HomeActivity;
import com.example.task7.Util.LoginJson;
import com.example.task7.Util.NetUtil;
import com.example.task7.level1.Level1Activity;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private TextView mTvReg;
    private Button mBtnLogin;
    private EditText mEtPassword;
    private EditText mEtUsername;
    private CheckBox mCkRemember;
    private boolean shouldRememberPassword;
    private TextView mTvForget;
    private Handler handler;
    String username;
    String password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler=new MyHandler();
        setContentView(R.layout.activity_login);
        initView();
        initEvent();

    }

    private void initView() {
        mBtnLogin = findViewById(R.id.btn_login_login);
        mEtUsername = findViewById(R.id.et_login_username);
        mEtPassword = findViewById(R.id.et_login_password);
        mTvReg = findViewById(R.id.tv_login_reg);
        mCkRemember = findViewById(R.id.ck_login_remember);
        mTvForget = findViewById(R.id.tv_login_forget);
    }

    private void initEvent() {
        displaySavedPassword();



        mBtnLogin.setOnClickListener(v -> login());

        mTvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });
        mTvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"暂未实现",Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 登录业务
     */
    private void login() {
        shouldRememberPassword = mCkRemember.isChecked();
        username = mEtUsername.getText().toString();
        password = mEtPassword.getText().toString();

        //输入账号不为空,密码不为空
        if (!username.isEmpty() && !password.isEmpty()) {
            Map<String,String> map = new HashMap<>();
            map.put("username",username);
            map.put("password",password);
            NetUtil.doPost("https://www.wanandroid.com/user/login", map,handler);
        }

    }

    public void displaySavedPassword() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", null);
        String savedPassword = sharedPreferences.getString("password", null);
        if (savedUsername != null && savedPassword != null) {
            mEtUsername.setText(savedUsername);
            mEtPassword.setText(savedPassword);
        } else {
            //mEtUsername.setText("");
            mEtUsername.setText(savedUsername);
            mEtPassword.setText("");
        }
    }

    private void rememberPassword(String username, String password) {
        SharedPreferences.Editor editor = getSharedPreferences("MyAppPreferences", MODE_PRIVATE).edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    private void clearSaved() {
        SharedPreferences.Editor editor = getSharedPreferences("MyAppPreferences", MODE_PRIVATE).edit();
        //editor.remove("username");
        editor.remove("password");
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        shouldRememberPassword = mCkRemember.isChecked();
        if (!shouldRememberPassword){
            clearSaved();
        }
        displaySavedPassword();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shouldRememberPassword = mCkRemember.isChecked();
        if (!shouldRememberPassword){
            clearSaved();
        }

    }

    private class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:{
                    LoginJson loginJson =LoginJson.decodeJson((String) msg.obj);//将登录返回的JOSN数据转换为loginjson类
                    if (loginJson.errorCode==-1){
                        mEtPassword.setText("");
                        Toast.makeText(LoginActivity.this ,loginJson.errorMsg,Toast.LENGTH_SHORT).show();
                    }
                    else{
                        shouldRememberPassword = mCkRemember.isChecked();
                        if (shouldRememberPassword){
                            rememberPassword(username,password);
                        }
                        Log.d("ld",loginJson.data.id+loginJson.data.publicName);
                        Intent intent =new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("user",loginJson.data.publicName);
                        startActivity(intent);
                    }
                    break;
                }
            }
        }
    }
}