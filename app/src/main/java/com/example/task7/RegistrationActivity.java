package com.example.task7;

import static com.example.task7.Util.NetUtil.doPost;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.task7.Util.LoginJson;
import com.example.task7.Util.NetUtil;
import com.example.task7.level1.Level1Activity;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    private Button mBtnReg;
    private EditText mEtPassword;
    private EditText mEtUsername;
    private EditText mEtRepassword;
    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            LoginJson loginJson = LoginJson.decodeJson((String) msg.obj);//将注册返回的JOSN数据转换为loginjson类
            if (loginJson.errorCode==-1){
                mEtPassword.setText("");
                mEtRepassword.setText("");
                Toast.makeText(RegistrationActivity.this ,loginJson.errorMsg,Toast.LENGTH_SHORT).show();
            }else{
                Log.d("ld",loginJson.data.id+loginJson.data.publicName);
                Intent intent =new Intent(RegistrationActivity.this, LoginActivity.class);
                Toast.makeText(RegistrationActivity.this,"注册成功，请登录",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initView();
        initEvent();

    }

    private void initEvent() {
        mBtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doReg();
            }
        });
    }

    private void initView() {
        mBtnReg = findViewById(R.id.btn_reg_reg);
        mEtUsername = findViewById(R.id.et_reg_username);
        mEtPassword = findViewById(R.id.et_reg_password);
        mEtRepassword = findViewById(R.id.et_reg_repassword);
    }


    /***
     * 注册业务
     */
    private void doReg(){
        String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();
        String repassword = mEtRepassword.getText().toString();


        //输入账号不为空,密码不为空
        if (username.length()>=3 && password.length()>=6) {
            Map<String,String> map = new HashMap<>();
            map.put("username",username);
            map.put("password",password);
            map.put("repassword",repassword);
            NetUtil.doPost("https://www.wanandroid.com/user/register", map, handler);
        }else{
            Toast.makeText(RegistrationActivity.this,"用户名不少于3位，密码不得短于6位",Toast.LENGTH_SHORT).show();
        }
    }
}