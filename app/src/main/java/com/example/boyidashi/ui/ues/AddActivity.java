package com.example.boyidashi.ui.ues;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.example.boyidashi.Application.MyApplicaiton;
import com.example.boyidashi.R;

import java.util.Date;

public class AddActivity extends Activity {
    private EditText etNum;
    private RadioGroup group;
    private RadioButton rbtnUsed, rbtnIncome;
    private TextView tvAdd;
    private MyApplicaiton applicaiton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
    }

    private void init() {
        applicaiton = (MyApplicaiton) getApplication();
        etNum = findViewById(R.id.et_addd_number);
        group = findViewById(R.id.rg_type);
        rbtnIncome = findViewById(R.id.rbtn_income);
        rbtnUsed = findViewById(R.id.rbtn_used);
        tvAdd = findViewById(R.id.tv_add);
        etNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        final int type = 0;
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.rbtn_income:
                        addNum(2, Float.parseFloat(etNum.getText().toString()));
                        break;
                    case R.id.rbtn_used:
                        addNum(1,  Float.parseFloat(etNum.getText().toString()));
                        break;


                }


            }
        });
    }


    /**
     * 上传添加
     */
    private void addNum(int uesType, float num) {
        AVObject avObject = new AVObject("used");
        Date date =new Date(System.currentTimeMillis());

        avObject.put("usetype", uesType);
        String username = applicaiton.getUserName();
        avObject.put("user", username);
        avObject.put("usednumber", num);
        avObject.put("date",date);
        avObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Toast.makeText(AddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();

                } else {
                    e.printStackTrace();
                    Toast.makeText(AddActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });


    }
}
