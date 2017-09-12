package com.jiyun.asus.day02_fist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Three_Activity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.tb_top_three)
    Toolbar tbTopThree;
    @Bind(R.id.et_phone_three)
    EditText etPhoneThree;
    @Bind(R.id.et_password_three)
    EditText etPasswordThree;
    @Bind(R.id.bu_zhu_three)
    Button buZhuThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_);
        ButterKnife.bind(this);
        showToolbar();
        buZhuThree.setOnClickListener(this);
    }

    private void showToolbar() {
        setSupportActionBar(tbTopThree);
        tbTopThree.setNavigationIcon(R.mipmap.u34);
        tbTopThree.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Three_Activity.this,Two_Activity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.bu_zhu_three:
                String phone = etPhoneThree.getText().toString();
                String password = etPhoneThree.getText().toString();
                if ("".equals(phone)||"".equals(password)){
                    Toast.makeText(Three_Activity.this,"信息不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }


               if (isMobileNO(phone)&&isRightPwd(password)){
                   startActivity(new Intent(Three_Activity.this,YanActivity.class));
               }
                break;
        }
    }
    //�验证密码����ȷ����
    public static final boolean isRightPwd(String pwd) {
        Pattern p = Pattern.compile("^(?![^a-zA-Z]+$)(?!\\D+$)[0-9a-zA-Z]{8,16}$");
        Matcher m = p.matcher(pwd);
        return m.matches();
    }

    //�验证手机号是否正确ֻ��
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
