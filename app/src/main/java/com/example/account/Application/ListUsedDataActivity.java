package com.example.account.Application;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.example.account.R;
import com.example.account.model.Used;

import java.util.ArrayList;
import java.util.List;

public class ListUsedDataActivity extends Activity {
    MyApplicaiton applicaiton;
    String username;
    List<Used> listUsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_useddata);
        applicaiton = (MyApplicaiton) getApplication();
        username = applicaiton.getUserName();
        listUsed = new ArrayList<>();
//        for (int i=0;i<5;i++){
//
//        AVObject avObject = new AVObject("used");
//        avObject.put("usetype", 1);
//        avObject.put("usednumber", i*100);
//        avObject.put("user", username);
//        avObject.saveInBackground();
//        }


//        avQuery.findInBackground(new FindCallback() {
//            @Override
//            public void done(List avObjects, AVException avException) {
//                Log.e("etstt", avObjects.toString() );
//
//            }
//
//            @Override
//            protected void internalDone0(Object o, AVException avException) {
//                    List<Object> list= (List<Object>) o;
//
//            }
//        });

    }

    private void getdata() {
        AVQuery avQuery = new AVQuery("used");
        avQuery.whereEqualTo("user", username);
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> avObjects, AVException avException) {
                for (AVObject avObject : avObjects) {
                    String username = avObject.getString("user");
//                    String data=avObject.getString("data");
                    float number = avObject.getInt("usednumber");
                    int useType = avObject.getInt("usetype");
                    listUsed.add(new Used(username, number, useType));

                }
            }
        });
    }


}
