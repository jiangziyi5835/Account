package com.example.account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;
import com.example.account.Application.ListUsedDataActivity;
import com.example.account.Application.MyApplicaiton;
import com.example.account.model.UserInfo;
import com.example.account.ui.login.LoginActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    MyApplicaiton myApplicaiton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myApplicaiton = (MyApplicaiton) getApplication();
        textView = findViewById(R.id.tv_first);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
//        SharedPreferences sharedPreferences=getSharedPreferences(MyApplicaiton.USERINFO,MODE_PRIVATE);
//         final AVObject avObject = new AVObject("used");
//        avObject.put("usetype", 15);
//        String username=myApplicaiton.getUserName();
//        avObject.put("user",username);
//        avObject.saveInBackground();

        if (!myApplicaiton.isFirstStart()) {
            Toast.makeText(this, "用户已登陆", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, ListUsedDataActivity.class));
        } else {
            Toast.makeText(this, "不是是第一次打开", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }




    }
}
