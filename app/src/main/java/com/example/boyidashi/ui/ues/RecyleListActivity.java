package com.example.boyidashi.ui.ues;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.example.boyidashi.Application.MyApplicaiton;
import com.example.boyidashi.R;
import com.example.boyidashi.model.UserData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecyleListActivity extends Activity {
    ExpandableListView rlvData;
    List<UserData> userData;
    MyApplicaiton applicaiton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylelist);
        applicaiton= (MyApplicaiton) getApplication();
        init();

    }

    private void init() {
        rlvData = findViewById(R.id.elv_data);
        userData = new ArrayList<>();


    }


    /**
     * 获取数据
     */
    private void getdata() {
        userData.clear();
        applicaiton.openDialog(this);
        AVQuery avQuery = new AVQuery("used");
        avQuery.whereEqualTo("user", applicaiton.getUserName());
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> avObjects, AVException avException) {
                userData.clear();
                for (AVObject avObject : avObjects) {
                    String username = avObject.getString("user");
//                    String data=avObject.getString("data");
                    Float number = Float.parseFloat(avObject.getDouble("usednumber") + "");
                    int useType = avObject.getInt("usetype");
                    Date date=avObject.getDate("usedate");



                }


                applicaiton.closeDialog();
            }
        });


    }
}
