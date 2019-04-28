package com.example.boyidashi.ui.regist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.example.boyidashi.R;
import com.example.boyidashi.ui.login.LoginActivity;

public class RegistActivity extends Activity implements View.OnClickListener {
    private EditText etUserName, etUserPsd;
    private TextView tvRegist, tvGotoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_regist);

        init();

    }

    private void init() {
        etUserName = findViewById(R.id.et_username);
        etUserPsd = findViewById(R.id.et_userpsd);
        tvRegist = findViewById(R.id.tv_regist);
        tvGotoLogin = findViewById(R.id.tv_gotoLogin);

        Drawable dwpsd = getResources().getDrawable(R.mipmap.icon_psd);
        dwpsd.setBounds(0, 0, 60, 60);
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_user);
        drawable.setBounds(0, 0, 60, 60);
        etUserName.setCompoundDrawables(drawable, null, null, null);
        etUserPsd.setCompoundDrawables(dwpsd, null, null, null);
        tvRegist.setOnClickListener(this);
        tvGotoLogin.setOnClickListener(this);


    }

    private void regist() {
        if (etUserName.getText().toString().equals("")) {
            Toast.makeText(this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            AVUser avUser = new AVUser();
            avUser.setUsername(etUserName.getText().toString());
            avUser.setPassword(etUserPsd.getText().toString());
            avUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistActivity.this,LoginActivity.class));
                    } else {
                            e.getCause();
                        Log.d("loginerror", e.getMessage().toString());
                    }

                }
            });
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_regist:
                regist();
                break;
            case R.id.tv_gotoLogin:
                startActivity(new Intent(RegistActivity.this, LoginActivity.class));

        }
    }
}
