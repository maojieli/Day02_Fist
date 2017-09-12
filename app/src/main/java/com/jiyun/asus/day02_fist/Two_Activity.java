package com.jiyun.asus.day02_fist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class Two_Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText pasword;
    private Button denglu;
    private ImageView qq;
    private ImageView weibo;
    private TextView zhaopaswold;
    private TextView zhuce;
    private G g;
    UMShareAPI share;
    private String nameString;
    private String paswordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_);
        initView();
        g = new G();
        share = UMShareAPI.get(this);
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                share.doOauthVerify(Two_Activity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
            }
        });


    }

    private void initView() {
        name = (EditText) findViewById(R.id.name);
        pasword = (EditText) findViewById(R.id.pasword);
        denglu = (Button) findViewById(R.id.denglu);
        qq = (ImageView) findViewById(R.id.qq);
        weibo = (ImageView) findViewById(R.id.weibo);
        zhaopaswold = (TextView) findViewById(R.id.zhaopaswold);
        zhuce = (TextView) findViewById(R.id.zhuce);

        denglu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.denglu:
                submit();
                boolean mobile = g.isMobile(nameString);
                if (mobile == true) {
                    Intent intent = new Intent(Two_Activity.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "登陆失败0", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void submit() {
        // validate
        nameString = name.getText().toString().trim();
        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        paswordString = pasword.getText().toString().trim();
        if (TextUtils.isEmpty(paswordString)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(Two_Activity.this).onActivityResult(requestCode, resultCode, data);
    }
}