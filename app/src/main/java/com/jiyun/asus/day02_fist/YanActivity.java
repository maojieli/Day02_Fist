package com.jiyun.asus.day02_fist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YanActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.bu_yan)
    CountdownButton buYan;


    @Bind(R.id.tb_top_yan)
    Toolbar tbTopYan;
    @Bind(R.id.et_yan)
    EditText etYan;

    @Bind(R.id.bu_ti)
    Button buTi;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yan);
        ButterKnife.bind(this);
        showToolbar();

        buTi.setOnClickListener(this);
        buYan.setOnClickListener(this);

    }

    private void showToolbar() {
        setSupportActionBar(tbTopYan);
        tbTopYan.setNavigationIcon(R.mipmap.u34);
        tbTopYan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(YanActivity.this, Three_Activity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bu_yan:
                i = (int) ((Math.random() * 9 + 1) * 100000);
                Toast.makeText(YanActivity.this, "" + i,
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.bu_ti:
                String s = etYan.getText().toString();
                int s1 = Integer.parseInt(s);
                if (s1 == i) {
                    SharedPreferences day02 = getSharedPreferences("Day02", 0);
                    SharedPreferences.Editor edit = day02.edit();
                    Intent intent = new Intent(YanActivity.this, Two_Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(YanActivity.this, "验证码不正确",
                            Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
