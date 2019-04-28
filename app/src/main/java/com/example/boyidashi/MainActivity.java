package com.example.boyidashi;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.example.boyidashi.ui.WebActivyty;
import com.example.boyidashi.ui.ues.ListUsedDataActivity;
import com.example.boyidashi.Application.MyApplicaiton;
import com.example.boyidashi.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    MyApplicaiton myApplicaiton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        AVObject avObject=new AVObject("open");
//        avObject.put("switch",false);
//        avObject.saveInBackground();
        myApplicaiton = (MyApplicaiton) getApplication();
//        textView = findViewById(R.id.tv_first);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            }
//        });

//        SharedPreferences sharedPreferences=getSharedPreferences(MyApplicaiton.USERINFO,MODE_PRIVATE);
        //         final AVObject avObject = new AVObject("used");
        //        avObject.put("usetype", 15);
        //        String username=myApplicaiton.getUserName();
        //        avObject.put("user",username);
        //        avObject.saveInBackground();


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
             open();
            }
        }, 3000);


    }

    private void firstStart() {
        if (myApplicaiton.getUserName().equals("")) {
            Toast.makeText(this, "不是是第一次打开,请先登陆", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "用户已登陆", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, ListUsedDataActivity.class));
            finish();
        }
    }

    private void open() {
        AVQuery<AVObject> avQuery = new AVQuery("open");
        avQuery.getInBackground("5cc020bca673f5006852a288", new GetCallback<AVObject>() {
                    @Override
                    public void done(AVObject object, AVException e) {
                        if (e == null) {
                            Boolean isOpen = object.getBoolean("switch");
                            if (isOpen) {
                                startActivity(new Intent(getApplication(), WebActivyty.class));
                                finish();
                            } else {
                                firstStart();
                            }
                        }
                    }
                }
        );


    }
}
