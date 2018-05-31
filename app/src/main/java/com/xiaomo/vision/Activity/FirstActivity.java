package com.xiaomo.vision.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xiaomo.vision.R;
import com.xiaomo.vision.Utils.Tools;

public class FirstActivity extends Activity {
    private Button login,exit,registered;
    private TextView forgetPwd;
    private EditText userName,userPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        init();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=userName.getText().toString();
                String pwd=userPwd.getText().toString();
                if (user=="小莫"&pwd=="vision@qq.com"){
                    Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Tools.showToast(getBaseContext(),"用户名/密码输入错误！");
                }
            }
        });
    }

    public void init() {
        login = (Button) findViewById(R.id.login);
        exit= (Button) findViewById(R.id.exit);
        registered= (Button) findViewById(R.id.registered);
        forgetPwd= (TextView) findViewById(R.id.forgetPwd);
        userName= (EditText) findViewById(R.id.user);
        userPwd= (EditText) findViewById(R.id.passWord);
    }
}
