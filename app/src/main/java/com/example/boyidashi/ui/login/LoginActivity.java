package com.example.boyidashi.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.example.boyidashi.ui.ues.ListUsedDataActivity;
import com.example.boyidashi.Application.MyApplicaiton;
import com.example.boyidashi.R;
import com.example.boyidashi.model.UserInfo;
import com.example.boyidashi.ui.regist.RegistActivity;

public class LoginActivity extends Activity implements View.OnClickListener {
    private EditText etUserName, etUserPsd;
    private TextView tvLogin, tvGotoRegist;
    private MyApplicaiton applicaiton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_login);

        init();

    }

    private void init() {
        applicaiton = (MyApplicaiton) getApplication();
        etUserName = findViewById(R.id.et_username);
        etUserPsd = findViewById(R.id.et_userpsd);
        tvLogin = findViewById(R.id.tv_login);
        tvGotoRegist = findViewById(R.id.tv_gotoRegist);
        Drawable dwpsd = getResources().getDrawable(R.mipmap.icon_psd);
        dwpsd.setBounds(0, 0, 60, 60);
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_user);
        drawable.setBounds(0, 0, 60, 60);
        etUserName.setCompoundDrawables(drawable, null, null, null);
        etUserPsd.setCompoundDrawables(dwpsd, null, null, null);
        tvLogin.setOnClickListener(this);
        tvGotoRegist.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                Login();
                break;
            case R.id.tv_gotoRegist:
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));


        }
    }

    /**
     * 登陆
     */
    private void Login() {
        if (etUserName.getText().toString().equals(""))
            Toast.makeText(applicaiton, "用户名不能为空", Toast.LENGTH_SHORT).show();
        else {
            String name = etUserName.getText().toString();
            String psd = etUserPsd.getText().toString();
            AVUser.logInInBackground(name, psd, new LogInCallback<AVUser>() {
                @Override
                public void done(AVUser user, AVException e) {
                    if (e == null) {
                        UserInfo userInfo = new UserInfo(user.getUsername());
                        applicaiton.updataUserInfo(userInfo);
                        Toast.makeText(applicaiton, "登陆成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, ListUsedDataActivity.class));
                        finish();
                    } else
                        Toast.makeText(applicaiton, e.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });
        }
    }
}
