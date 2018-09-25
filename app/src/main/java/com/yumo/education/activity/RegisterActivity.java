package com.yumo.education.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yumo.education.R;
import com.yumo.education.bean.RegisterBean;
import com.yumo.education.bean.SendSmsBean;
import com.yumo.education.utils.CountDownTimerUtils;
import com.yumo.education.utils.MyUrl;
import com.yumo.education.utils.SharedPreferenceUtil;
import com.yumo.education.utils.ToastUtils;
import com.yumo.education.utils.ZhengzeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.et_yanzhengma)
    EditText etYanzhengma;
    @BindView(R.id.et_yaoqingma)
    EditText etYaoqingma;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.login_progress)
    ProgressBar loginProgress;
    @BindView(R.id.head_img)
    RelativeLayout headImg;
    @BindView(R.id.mTv)
    TextView mTv;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.et_pass)
    EditText et_pass;
    @BindView(R.id.tv_tip2)
    TextView tv_tip2;
    @BindView(R.id.et_pass2)
    EditText et_pass2;
    @BindView(R.id.checkBox2)
    CheckBox checkBox2;
    @BindView(R.id.button)
    Button button;
    String type1 = "1";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setTitle("开始注册");
//        String vacode = etYanzhengma.getText().toString().trim();
//        String incode = etYaoqingma.getText().toString().trim();
//        String type1 = "1";

        String username = etUser.getText().toString().trim();
         String password = et_pass.getText().toString().trim();
         String repassword = et_pass2.getText().toString().trim();

        //初始化View控件
        etUser = (EditText) findViewById(R.id.et_user);
        et_pass = (EditText) findViewById(R.id.et_pass);
        tvTip = (TextView) findViewById(R.id.tv_tip);
        button = (Button) findViewById(R.id.button);
        tv_tip2 = (TextView) findViewById(R.id.tv_tip2);
        mTv = (TextView) findViewById(R.id.mTv);//获取验证码倒计时
        et_pass2 = (EditText) findViewById(R.id.et_pass2);

        //先设置不让点按钮 灰色
//        button.setEnabled(false);
//        button.setClickable(false);
//        button.setBackgroundResource(R.drawable.btn_bg_pressed);

        //不是手机号不让发验证码
//
//        if (ZhengzeUtils.isChinaPhoneLegal(etUser.getText().toString())){
//            mTv.setText("获取验证码");
//            mTv.setEnabled(true);
////            Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
//        }else {
//            mTv.setText("手机号不正确");
//            mTv.setEnabled(false);
//        }
//        //用于检测输入的用户名操作
//        //为etUser设置焦点改变监听事件
//        etUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                //如果从用户名失去焦点（光标不在编辑框）则进行用户名的检测
//                if (!etUser.hasFocus()) {
//                    //如果用户名长度小于10或者大于12，则提示用户名错误且登陆不可点击
////                    if (etUser.getText().toString().length() > 12 || etUser.getText().toString().length() < 10||etUser.getText().toString() == "") {
//                    if (!ZhengzeUtils.isChinaPhoneLegal(etUser.getText().toString())) {
//                        tvTip.setText("手机号不合法！");
//                        tvTip.setTextColor(Color.RED);
//                        // button.setClickable(false);
//                        //如果失去焦点则进行mimA的检测
//                    } else {
//                        tvTip.setTextColor(Color.RED);
//                        tvTip.setText("请输入密码");
//                        // if (!et_pass.hasFocus()) {
//                        //如果密码长度小于6或者大于20，则提示密码错误且登陆不可点击
////                            if (et_pass.getText().toString().length() > 20 || et_pass.getText().toString().length() < 6) {
//                        if (TextUtils.isEmpty(et_pass.getText().toString()) || !ZhengzeUtils.isMima(et_pass.getText().toString()) || !password.equals(repassword)) {
//                            tv_tip2.setText("密码为6-20位字母或数字组合！");
//                            tv_tip2.setTextColor(Color.RED);
//
//
//                        } else {
//                            tvTip.setText("请记好您的密码");
//                            tv_tip2.setText("请记好您的密码");
//                            //如果用户名合法且密码不为空，设置提示字体消失按钮可点击
////
//                            if (checkBox2.isChecked()) {
//
//                                button.setClickable(true);//设置可点击
//                                button.setEnabled(true);///设置可用
//                                button.setBackgroundResource(R.drawable.btn_selector);
//                            }
//                            //}
////                                }
//                        }
//
//                        // }
//                    }
//
//                }
//            }
//        });//


        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ZhengzeUtils.isChinaPhoneLegal(username)) {
            //是手机号才能继续进行
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "请输入6-20位字母数字组合密码", Toast.LENGTH_SHORT).show();
                //return;
            }
            //判断2次密码相同不
            if (TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
                // 如果有一个为空，错误返回，这里处理的逻辑我就不写了，直接返回
                // 一般给个Toast提醒一下吧
                Toast.makeText(this, "两次密码不一致或为空", Toast.LENGTH_SHORT).show();
            //    return;
            }
            if (!password.equals(repassword)) {
                // 两次输入的密码不一致
                Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();

               // return;
            }
            // 后面是两次密码一致通过验证的逻辑

            if (password.equals(repassword) && ZhengzeUtils.isMima(et_pass.getText().toString().trim())) {
                if (checkBox2.isChecked()) {
                    Toast.makeText(this, "请求注册中", Toast.LENGTH_SHORT).show();
                    button.setEnabled(true);
                    button.setClickable(true);
                    button.setBackgroundResource(R.drawable.btn_selector);
                    //登录网路请求 点击按钮才可以 //  registerHttp(username, password, vacode, incode, type1);
                 //   return;
                } else {
                    Toast.makeText(this, "注册协议未勾选", Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
           // return;
        }


        //注册协议
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && !TextUtils.isEmpty(etUser.getText().toString()) && !TextUtils.isEmpty(et_pass.getText().toString())) {
                    //如果选中，注册按钮可被点击
                    button.setClickable(true);
                } else {
                    //否则注册按钮不可用
                    button.setClickable(false);
                    button.setEnabled(false);
                }

            }
        });
        //点击注册
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //试以pass为准提交请求。
                Toast.makeText(RegisterActivity.this, "注册", Toast.LENGTH_SHORT).show();
                registerHttp(etUser.getText().toString().trim(), et_pass.getText().toString().trim(), etYaoqingma.getText().toString().trim(), etYanzhengma.getText().toString().trim(), type1);//登录网路请求

            }
        });

    }


    /***
     * 注册网络请求
     */
    private void registerHttp(String phoneNumber, String passWord, String invitationCode, String validateCode, String type) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("passWord", passWord);
        map.put("invitationCode", invitationCode);
        map.put("validateCode", validateCode);
        map.put("type", type);

        OkHttpUtils.post().url(MyUrl.register).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("mhy", request + "---------" + e);

            }

            @Override
            public void onResponse(String response) {
                Log.d("mhy", "register" + response);//获取登录结果json
                RegisterBean bean = new Gson().fromJson(String.valueOf(response), RegisterBean.class);//
                if (bean.getResultCode() == 0) {
                   // SharedPreferenceUtil.SaveData("register", bean.getData() + "");
                    Toast.makeText(RegisterActivity.this, "展示给用户的注册结果" + bean.getMsg(), Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();//防止返回键又回来登录
                } else {
                    Toast.makeText(RegisterActivity.this, "注册结果" + bean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /***
     * 设置头像
     * @param view
     */
    public void setPhoto(View view) {
        Toast.makeText(this, "请选中图片设置为头像", Toast.LENGTH_SHORT).show();
////////////////////////
    }

    /***
     * 发送验证码
     * @param view
     */
    public void getSMS(View view) {

        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(mTv, 60000, 1000);
        mCountDownTimerUtils.start();
        Toast.makeText(this, "请注意查收验证码", Toast.LENGTH_SHORT).show();
        //验证码请求是吧手机号发到后台
        sendSMSHttp(etUser.getText().toString());


    }

    /***
     * 发送验证码后台请求
     */
    private void sendSMSHttp(String phone) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        OkHttpUtils.post().url(MyUrl.sendSMS).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("mhy", request + "---------" + e);

            }

            @Override
            public void onResponse(String response) {
                Log.d("mhy", "sendsms" + response);//获取登录结果json
                SendSmsBean bean = new Gson().fromJson(String.valueOf(response), SendSmsBean.class);//
                if (bean.getResultCode() == 0) {
                  //  SharedPreferenceUtil.SaveData("sendsms", bean.getData() + "");
                    Toast.makeText(RegisterActivity.this, "验证码已发送" + bean.getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(RegisterActivity.this, "展示给用户验证码请求的结果" + bean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void xieyi(View view) {
        //点击协议，出Alert弹窗，点击确认单选框也同时勾选
        new AlertDialog.Builder(this)
                //最好找个xml绑定显示协议内容
                // .setIcon(android.R.drawable.ic_dialog_info)//这里是显示提示框的图片信息
                .setTitle("注册协议")
                .setMessage("请您认真阅读：\n协议内容")
                .setNegativeButton("取消", null)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //点了确认就让单选框勾选
                        checkBox2.setChecked(true);
                    }
                }).show();
    }


}
