package com.jiyun.asus.day02_fist.utlis;

import android.os.CountDownTimer;
import android.widget.Button;

import com.jiyun.asus.day02_fist.R;


/**
 * Created by lenovo on 2017/8/27.
 */

public class TimeCount extends CountDownTimer {

    private  Button button;

    public TimeCount(Button button, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.button = button;
    }

    @Override
    public void onFinish() {// 计时完毕
        button.setText("重新获取");
        button.setClickable(true);
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程
        button.setClickable(false);//防止重复点击
        button.setBackgroundResource(R.color.clickbackgroud);
        button.setText(millisUntilFinished / 1000 +"秒后重新获取");
    }
}
