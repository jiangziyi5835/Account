package com.example.boyidashi.Application;


import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;

import com.avos.avoscloud.AVOSCloud;

import com.example.boyidashi.R;
import com.example.boyidashi.model.UserInfo;

public class MyApplicaiton extends Application {
    private UserInfo userInfo;
    private SharedPreferences mSharedPreferences;
    public final static String ISFIRSTSTART = "isFirstStart";
    public final static String USRR_NAME = "usename";
    public final static String USERINFO = "UserInfo";
    private static Dialog dialog;
    private static int dialogNum;

    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "1KDkCYJkUUFB77kW1UX4VqSM-gzGzoHsz", "g6EwACJgBuffQrsGtA0WmNzY");
        mSharedPreferences = getSharedPreferences(USERINFO, MODE_PRIVATE);

    }

    public UserInfo getUserInfo() {
        userInfo.setName(mSharedPreferences.getString(USRR_NAME, ""));
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

    /**
     * 打开加载条
     *
     * @param context
     */


    public void openDialog(Context context) {
        if (dialog == null) {
            CreateLoadingDialog(context, "加载中…").show();
        } else {
            dialog.show();
        }
    }

    /**
     * 关闭加载条
     */
    public void closeDialog() {
        if (dialog != null) {
            --dialogNum;
            if (dialogNum == 0) {
                dialog.dismiss();
                dialog = null;
            }
        }
    }

    private static Dialog CreateLoadingDialog(Context context, String msg) {
        dialogNum = dialogNum + 1;
        if (dialog == null) {
            View view = LayoutInflater.from(context).inflate(R.layout.view_loading, null);
            dialog = new Dialog(context, R.style.loading_dialog);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setContentView(view);
            return dialog;
        } else {
            return dialog;
        }


    }
//
//    private static Dialog CreateLoadingDialog(Context context, String msg) {
//        dialogNum = dialogNum + 1;
//        if (dialog == null) {
//            LayoutInflater inflater = LayoutInflater.from(context);
//            View view = inflater.inflate(R.layout.view_loading, null);//得到加载view
//            LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);
//            ImageView spaceshipImage = (ImageView) view.findViewById(R.id.img);
//            Animation animation = AnimationUtils.loadAnimation(context, R.anim.anmi);
//            spaceshipImage.startAnimation(animation);
//            dialog = new Dialog(context, R.style.loading_dialog);
//            dialog.setCancelable(false);
//            dialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//            return dialog;
//        } else {
//            return dialog;
//        }
//    }


    public void clearUserInfo() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.putBoolean(ISFIRSTSTART, true);
        editor.commit();
        userInfo = null;

    }

    public boolean isFirstStart() {
        return mSharedPreferences.getBoolean(ISFIRSTSTART, false);

    }

    public String getUserName() {
        return mSharedPreferences.getString(USRR_NAME, "");
    }
}
