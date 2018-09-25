package com.yumo.education.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yumo.education.R;
import com.yumo.education.fragment.ClassFragment;
import com.yumo.education.fragment.HomeFragment;
import com.yumo.education.fragment.MineFragment;
import com.yumo.education.fragment.QianyueFragment;
import com.yumo.education.fragment.ShopCardFragment;
import com.yumo.education.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    Context context;
    @BindView(R.id.main_layout)
    FrameLayout mainLayout;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    private TextView tv1, tv2, tv3, tv4, tv5;
    private ImageView img1, img2, img3, img4, img5;
    private Fragment[] fgtArr;
    private int prePosition = -1;

    HomeFragment fragment1;
    ClassFragment fragment2;
    QianyueFragment fragment3;
    ShopCardFragment fragment4;
    MineFragment fragment5;
    private ImageView mMianIbBack;
   // private EditText et;
    private TextView mMainTvBar;
    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //判断未登录去登录
        if (TextUtils.isEmpty(SharedPreferenceUtil.getStringData("userId"))) {
//            tv5.setText("未登录");

            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
//        }else {
//            tv5.setText("我的");
        }
        context = this;
        ButterKnife.bind(this);
        initView();
        mMianIbBack = (ImageView) findViewById(R.id.mian_ib_back);
        mMainTvBar = (TextView) findViewById(R.id.main_tv_bar);


        //   MAC地址//获取wifi服务
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        assert wm != null;
        @SuppressLint("HardwareIds") String MAC = wm.getConnectionInfo().getMacAddress();
        //  IMEI:
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        //获取IMEI
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        assert telephonyManager != null;
        @SuppressLint("HardwareIds") String IMEI = telephonyManager.getDeviceId();
        Toast.makeText(context, MAC + "\n" + IMEI, Toast.LENGTH_SHORT).show();
        mMainTvBar.setText(MAC + "\n" + IMEI);
        //判断wifi是否开启
        if (!wm.isWifiEnabled()) {
            wm.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wm.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        EditText et = (EditText) findViewById(R.id.EditText01);
        et.setText(ip);


        mMianIbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // startActivity(new Intent(Intent.ACTION_MAIN).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addCategory(Intent.CATEGORY_HOME));
               startActivity(new Intent(MainActivity.this,InfoActivity.class));
           // finish();
            }
        });
    }


//地址分割
    private String intToIp(int i) {

        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }
    //

    private void initView() {
        fgtArr = new Fragment[5];

        fragment1 = new HomeFragment();
        fragment2 = new ClassFragment();
        fragment3 = new QianyueFragment();
        fragment4 = new ShopCardFragment();
        fragment5 = new MineFragment();

        fgtArr[0] = fragment1;
        fgtArr[1] = fragment2;
        fgtArr[2] = fragment3;
        fgtArr[3] = fragment4;
        fgtArr[4] = fragment5;

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
//默认设定显示内容
        changeFgt(0);
        img1.setImageResource(R.mipmap.icon_home_pre);
        tv1.setTextColor(ContextCompat.getColor(context, R.color.themeColor));
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment1).commit();
    }

    @OnClick({R.id.ly1, R.id.ly2, R.id.ly3, R.id.ly4, R.id.ly5})
    public void OnClick(View v) {
        setView();//每次点击按钮之前先把所有按钮置灰，然后点谁，谁再走设置的内容。
        switch (v.getId()) {
            case R.id.ly1:
                img1.setImageResource(R.mipmap.icon_home_pre);
                tv1.setTextColor(ContextCompat.getColor(context, R.color.themeColor));
                changeFgt(0);
                break;
            case R.id.ly2:
                img2.setImageResource(R.mipmap.icon_fenlei_pre);
                tv2.setTextColor(ContextCompat.getColor(context, R.color.themeColor));
                changeFgt(1);
                break;
            case R.id.ly3:
                img3.setImageResource(R.mipmap.icon_qianyue_pre);
                tv3.setTextColor(ContextCompat.getColor(context, R.color.themeColor));
                changeFgt(2);
                break;
            case R.id.ly4://
                img4.setImageResource(R.mipmap.icon_gouwuche_pre);
                tv4.setTextColor(ContextCompat.getColor(context, R.color.themeColor));
                changeFgt(3);
                break;
            case R.id.ly5://
                img5.setImageResource(R.mipmap.icon_mine_pre);
                tv5.setTextColor(ContextCompat.getColor(context, R.color.themeColor));
                changeFgt(4);
                Toast.makeText(context, "zheshi" + v.getId(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setView() {
        tv1.setTextColor(ContextCompat.getColor(context, R.color.text_navigation));
        tv2.setTextColor(ContextCompat.getColor(context, R.color.text_navigation));
        tv3.setTextColor(ContextCompat.getColor(context, R.color.text_navigation));
        tv4.setTextColor(ContextCompat.getColor(context, R.color.text_navigation));
        tv5.setTextColor(ContextCompat.getColor(context, R.color.text_navigation));

        img1.setImageResource(R.mipmap.icon_home_nor);
        img2.setImageResource(R.mipmap.icon_fenlei_nor);
        img3.setImageResource(R.mipmap.icon_qianyue_nor);
        img4.setImageResource(R.mipmap.icon_gouwuche_nor);
        img5.setImageResource(R.mipmap.icon_mine_nor);
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
            transaction.add(R.id.main_layout, fgtArr[curPosition]).show(fgtArr[curPosition]);
        }
        transaction.commit();
        prePosition = curPosition;
    }

    @Override
    public int setBaseView() {
        return R.layout.activity_main;
    }

    @Override
    public boolean showTitle() {
        return false;
    }

    @Override
    public String setTitleText() {
        return "主页";
    }


    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("确认退出吗？")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
//                        MainActivity.this.finish();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(intent);
//                        System.exit(0);// 使虚拟机停止运行并退出程序
                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).setNeutralButton("xx", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
                System.exit(0);
            }
        }).show();
    }


}
