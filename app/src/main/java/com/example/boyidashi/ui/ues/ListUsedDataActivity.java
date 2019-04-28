package com.example.boyidashi.ui.ues;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CloudQueryCallback;
import com.avos.avoscloud.FindCallback;
import com.example.boyidashi.Adapter.ListUseddataAdapter;
import com.example.boyidashi.Application.MyApplicaiton;
import com.example.boyidashi.R;
import com.example.boyidashi.model.Used;
import com.example.boyidashi.ui.login.LoginActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListUsedDataActivity extends Activity {
    private MyApplicaiton applicaiton;
    private String username;
    private List<Used> listUsed;
    private ListView lvUsed;
    private ListUseddataAdapter adapter;
    private TextView tvAdd, tvCount, tvExitLogin, tvUser;
    private Dialog dialog;
    private float totalNum = 0, totalUesd = 0, totalInconm = 0;
    private String objectID = "";
    private AlertDialog.Builder deleteDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_useddata);
        applicaiton = (MyApplicaiton) getApplication();
        username = applicaiton.getUserName();
        listUsed = new ArrayList<>();
        init();


        lvUsed = findViewById(R.id.lv_used);
        adapter = new ListUseddataAdapter(listUsed, this);
        lvUsed.setAdapter(adapter);

        getdata();
        adapter.notifyDataSetChanged();
        lvUsed.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                objectID = listUsed.get(position).getId();
                deleteDialog.show();

                return false;
            }
        });


    }

    /**
     * 初始化
     */
    private void init() {
        tvAdd = findViewById(R.id.tv_add);
        tvCount = findViewById(R.id.tv_count);
        tvExitLogin = findViewById(R.id.tv_exitlogin);
        tvUser = findViewById(R.id.tv_user);
        deleteDialog = new AlertDialog.Builder(this);//确定删除记录的对话框
        tvUser.setText(applicaiton.getUserName());
        deleteDialog.setCancelable(true);
        deleteDialog.setTitle("是否确定删除这条记录");
        deleteDialog.setMessage("点击删除将从云端删除该条记录");
        deleteDialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteData();
            }
        });
        deleteDialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        tvAdd.setOnClickListener(new View.OnClickListener() {//添加按钮点击事件
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListUsedDataActivity.this, AddActivity.class));
            }
        });
        tvCount.setOnClickListener(new View.OnClickListener() {//统计按钮点击事件
            @Override
            public void onClick(View v) {
                countData();
            }
        });
        tvExitLogin.setOnClickListener(new View.OnClickListener() {//退出登录事件
            @Override
            public void onClick(View v) {
                applicaiton.clearUserInfo();
                startActivity(new Intent(ListUsedDataActivity.this, LoginActivity.class));
            }
        });
    }

    /**
     * 删除记录
     */
    private void deleteData() {
        AVQuery.doCloudQueryInBackground("delete from used where objectId='" +
                objectID +
                "'", new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                // 如果 e 为空，说明保存成功
                if (e == null) {
                    Toast.makeText(ListUsedDataActivity.this, "记录删除成功", Toast.LENGTH_SHORT).show();
                    getdata();
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("deletefaile", e.getMessage());
                    Toast.makeText(ListUsedDataActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 统计数据
     * useType=1为支出
     * useType=2为收入
     */
    private View view;

    private void countData() {
        totalUesd = 0;
        totalNum = 0;
        totalInconm = 0;
        for (Used used : listUsed) {
            if (used.getUseType() == 1) {
                totalUesd = totalUesd + used.getNumber();
            } else {
                totalInconm = totalInconm + used.getNumber();
            }
            totalNum = totalInconm - totalUesd;

        }
        dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.view_count, null);
        TextView tvTotalSum = view.findViewById(R.id.tv_total_sum);
        TextView tvIncomSum = view.findViewById(R.id.tv_income_sum);
        TextView tvUsedSum = view.findViewById(R.id.tv_used_sum);
        ImageView ivExit = view.findViewById(R.id.iv_exit);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        tvTotalSum.setText(decimalFormat.format(totalNum) + "");
        tvIncomSum.setText(decimalFormat.format(totalInconm) + "");
        tvUsedSum.setText(decimalFormat.format(totalUesd) + "");
        dialog.setContentView(view);
        dialog.setCancelable(true);

        dialog.show();
        ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    /**
     * 测试数据
     */
    private void fuGetData() {
        for (int i = 0; i < 10; i++) {
            listUsed.add(new Used("jiang", 100 + i, 2));

        }
    }

    /**
     * 获取数据
     */
    private void getdata() {

        applicaiton.openDialog(this);
        AVQuery avQuery = new AVQuery("used");
        avQuery.orderByDescending("createdAt");
        avQuery.whereEqualTo("user", username);
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> avObjects, AVException avException) {
                if (avException == null) {
                    listUsed.clear();


                    for (AVObject avObject : avObjects) {
                        String username = avObject.getString("user");
                        Date date = avObject.getDate("date");
                        Float number = Float.parseFloat(avObject.getDouble("usednumber") + "");
                        int useType = avObject.getInt("usetype");
                        String id = avObject.getObjectId();
                        listUsed.add(new Used(id, username, number, useType, date));

                    }

                    adapter.notifyDataSetChanged();
                    applicaiton.closeDialog();
                } else {
                    Toast.makeText(applicaiton, avException.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getdata();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getdata();
        adapter.notifyDataSetChanged();

    }
//
//    /**
//     * 上传添加
//     */
//    private void addNum(int uesType, float num) {
//        final AVObject avObject = new AVObject("used");
//        avObject.put("usetype", uesType);
//        String username = applicaiton.getUserName();
//        avObject.put("user", username);
//        avObject.put("usednumber", num);
//        avObject.saveInBackground();
//    }


}
