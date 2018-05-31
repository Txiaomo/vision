package com.xiaomo.vision.MyPage;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.xiaomo.vision.R;
import com.xiaomo.vision.Utils.Tools;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.options.RegisterOptionalUserInfo;
import cn.jpush.im.api.BasicCallback;

public class Register extends Activity implements View.OnClickListener {
    private EditText user, tel, pwd, checkPwd, code;
    private Button getCode, register;
    private ImageButton exit;
    private String userName, passWord;
    private RegisterOptionalUserInfo registerOptionalUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        getCode.setOnClickListener(this);
        register.setOnClickListener(this);
        exit.setOnClickListener(this);
    }


    private void initView() {
        user = (EditText) findViewById(R.id.editUser);
        tel = (EditText) findViewById(R.id.editTel);
        pwd = (EditText) findViewById(R.id.editPwd);
        checkPwd = (EditText) findViewById(R.id.editCheckPwd);
        code = (EditText) findViewById(R.id.editCode);
        getCode = (Button) findViewById(R.id.getCode);
        register = (Button) findViewById(R.id.inputCheck);
        exit = (ImageButton) findViewById(R.id.imageButton);
        registerOptionalUserInfo = new RegisterOptionalUserInfo();
    }


    @Override
    public void onClick(View v) {
        if (v == code) {

        } else if (v == register) {
            userName = user.getText().toString();
            passWord = pwd.getText().toString();
            registerOptionalUserInfo.setBirthday(199999);
            JMessageClient.register(userName, passWord, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    switch (i) {
                        case 0:
                            Tools.showToast(getBaseContext(), "注册成功！");
                            Register.this.finish();
                            break;
                        case 871102 | 871310:
                            Tools.showToast(getBaseContext(), "请检查网络后重试！");
                            break;
                        case 871303:
                            Tools.showToast(getBaseContext(), "用户名不合法！");
                            break;
                        case 871304:
                            Tools.showToast(getBaseContext(), "密码不合法！");
                            break;
                        case 871305:
                            Tools.showToast(getBaseContext(), "名称输入不合法！");
                            break;
                    }
                }
            });
        } else if (v == exit) {
            this.finish();
        } else {

        }
    }
}
