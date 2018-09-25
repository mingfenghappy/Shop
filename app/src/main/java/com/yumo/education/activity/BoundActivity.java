package com.yumo.education.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yumo.education.R;
import com.yumo.education.bean.BoundBean;
import com.yumo.education.bean.RegisterBean;
import com.yumo.education.bean.SendSmsBean;
import com.yumo.education.fragment.BoundAccountFragment;
import com.yumo.education.fragment.BoundPhoneFragment;
import com.yumo.education.utils.MyUrl;
import com.yumo.education.utils.SharedPreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BoundActivity extends AppCompatActivity {
    BoundPhoneFragment fragment1;
    BoundAccountFragment fragment2;
    Fragment[] fgtArr;
    @BindView(R.id.boundphone)
    Button boundphone;
    @BindView(R.id.boundaccount)
    Button boundaccount;
    @BindView(R.id.bound_layout)
    FrameLayout boundLayout;
    private int prePosition = -1;
    String openID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("绑定账户");
        setContentView(R.layout.activity_bound);
        ButterKnife.bind(this);
        fragment1 = new BoundPhoneFragment();
        fragment2 = new BoundAccountFragment();
        fgtArr = new Fragment[2];
        fgtArr[0] = fragment1;
        fgtArr[1] = fragment2;

        Intent i =getIntent();
       //通过“openId”关键字进行获取传来值
       openID=i.getStringExtra("openId");

     //  changeFgt(0);//默认第0页显示
        getSupportFragmentManager().beginTransaction().replace(R.id.bound_layout, fragment1).commit();//默认布局1界面显示
        boundphone.setBackgroundColor(R.drawable.bg_identify_code_normal);
    }
    public String getTitles(){
        return openID;
    }

    @OnClick({R.id.boundphone, R.id.boundaccount})
    public void onViewClicked(View view) {
        setView();//每次点击按钮之前先把所有按钮置灰，然后点谁，谁再走设置的内容。
        switch (view.getId()) {
            case R.id.boundphone:
                changeFgt(0);
                boundphone.setBackgroundResource(R.drawable.bg_identify_code_normal);
                boundaccount.setBackgroundResource(R.drawable.bg_identify_code_press);
                // getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment1).commit();//布局1界面显示
                break;
            case R.id.boundaccount:
                // getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment2).commit();//布局2界面显示
              changeFgt(1);
                boundaccount.setBackgroundResource(R.drawable.bg_identify_code_normal);
                boundphone.setBackgroundResource(R.drawable.bg_identify_code_press);
                break;
        }
    }
    private void setView() {
        boundphone.setBackgroundColor(R.drawable.bg_identify_code_press);
        boundaccount.setBackgroundColor(R.drawable.bg_identify_code_press);

    }
    private void changeFgt(int curPosition) {
        if (curPosition == prePosition) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        if (prePosition != -1)
            transaction.hide(fgtArr[prePosition]);


        if (fgtArr[curPosition].isAdded()) {
            transaction.show(fgtArr[curPosition]);
        } else {
            transaction.add(R.id.bound_layout, fgtArr[curPosition]).show(fgtArr[curPosition]);
        }
        transaction.commit();
        prePosition = curPosition;
    }

}
