package com.example.account.Application;

import android.app.Application;
import android.content.SharedPreferences;

import com.avos.avoscloud.AVOSCloud;
import com.example.account.model.UserInfo;

public class MyApplicaiton extends Application {
    private UserInfo userInfo;
    private SharedPreferences mSharedPreferences;
    public final static String ISFIRSTSTART = "isFirstStart";
    public final static String USRR_NAME = "usename";
    public final static String USERINFO="UserInfo";

    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "mXTousf2jlqKQOMyxk6bSq20-gzGzoHsz", "M93snsObxVQHHqnmhtPMhYL2");
        mSharedPreferences=getSharedPreferences(USERINFO,MODE_PRIVATE);

    }

    public UserInfo getUserInfo() {
       userInfo.setName(mSharedPreferences.getString(USRR_NAME,""));
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void updataUserInfo(UserInfo user) {
        this.userInfo = user;
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(USRR_NAME, user.getName());
        editor.putBoolean(ISFIRSTSTART, false);
        editor.commit();

    }


    public void clearUserInfo() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.putBoolean(ISFIRSTSTART, true);
        editor.commit();
        userInfo = null;

    }
    public boolean isFirstStart(){
      return mSharedPreferences.getBoolean(ISFIRSTSTART,false);

    }

    public String getUserName() {
        return mSharedPreferences.getString(USRR_NAME,"");
    }
}
