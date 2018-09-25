package com.yumo.education.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.yumo.education.R;
import com.yumo.education.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：启动页面
 * 作  者：蔡佳彬
 * 时  间：2017-06-03
 * 修改备注：
 */
public class StartActivity extends BaseActivity {
    @BindView(R.id.start)
    ImageView start;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
       // start.setImageResource(R.mipmap.start);
       // start.setImageResource(R.color.line);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isFirst = SharedPreferenceUtil.getBoolean(StartActivity.this,"isFirst",true);
                if (isFirst) {
                    SharedPreferenceUtil.putBoolean(StartActivity.this,"isFirst",false);
                    startActivity(new Intent(StartActivity.this,GuideActivity.class));
                } else {
                    startActivity(new Intent(StartActivity.this,MainActivity.class));
                }
                finish();
//                if (!SharedPreferenceUtil.getBooleanData("isFirst")) {
//                    startActivity(new Intent(StartActivity.this, GuideActivity.class));
//                } else {
//                    startActivity(new Intent(StartActivity.this, MainActivity.class));
//                }
//                finish();
            }
        }, 1500);
    }

    @Override
    public int setBaseView() {
        return R.layout.activity_start;
    }

    @Override
    public boolean showTitle() {
        return false;
    }

    @Override
    public String setTitleText() {
        return null;
    }


}
