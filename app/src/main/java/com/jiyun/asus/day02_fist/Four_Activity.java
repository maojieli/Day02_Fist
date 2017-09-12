package com.jiyun.asus.day02_fist;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jiyun.asus.day02_fist.utlis.TimeCount;

import java.util.ArrayList;

public class Four_Activity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar tb;
    private EditText verification;
    private Button getverification;
    private Button commit;
    private EditText mNumber;
    private StringBuffer sb;
    private String verificationString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_);
        initView();
        initToolBar();


    }

    private void initToolBar() {
        tb.setTitle("找回密码");
        tb.setTitleTextColor(Color.WHITE);
        tb.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(tb);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭
                finish();
            }
        });
    }


    private void initView() {
        tb = (Toolbar) findViewById(R.id.tb);
        verification = (EditText) findViewById(R.id.verification);
        getverification = (Button) findViewById(R.id.getverification);
        commit = (Button) findViewById(R.id.commit);
        mNumber = (EditText) findViewById(R.id.mNumber);

        getverification.setOnClickListener(this);
        commit.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getverification:
                TimeCount timeCount = new TimeCount(getverification, 60000, 1000);
                timeCount.start();
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i <4 ; i++) {
                    double random = Math.random();
                    int  v1 = (int) (random * 10);
                    list.add(String.valueOf(v1));
                }
                sb = new StringBuffer();
                for (int i = 0; i <list.size() ; i++) {
                    sb.append(list.get(i));
                }
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(Four_Activity.this);
                builder.setSmallIcon(R.mipmap.ic_launcher)
                        .setContentText(sb.toString())
                        .setTicker("xxx用户服务中心发来一条消息");
                Notification notification = builder.build();
                manager.notify(1,notification);
                break;
            case R.id.commit:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        verificationString = verification.getText().toString().trim();
        if (TextUtils.isEmpty(verificationString)) {
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }


        String mNumberString = mNumber.getText().toString().trim();
        if (TextUtils.isEmpty(mNumberString)) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (verificationString.equals(sb.toString())){
            Intent intent = new Intent(Four_Activity.this, Two_Activity.class);
            startActivity(intent);
        }

        // TODO validate success, do something


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
