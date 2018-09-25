package com.yumo.education.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yumo.education.R;
import com.yumo.education.bean.ChangePassBean;
import com.yumo.education.bean.SendSmsBean;
import com.yumo.education.utils.DiyView.BackTimeView;
import com.yumo.education.utils.IntentUtils;
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

public class ChangePassActivity extends AppCompatActivity {

    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_yanzhengma)
    EditText etYanzhengma;
    @BindView(R.id.register_timer)
    BackTimeView registerTimer;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.tv_tip2)
    TextView tvTip2;
    @BindView(R.id.et_pass2)
    EditText etPass2;
    @BindView(R.id.button_change)
    Button buttonChange;
    @BindView(R.id.login_titleBar_iv_back)
    ImageView loginTitleBarIvBack;
    @BindView(R.id.login_titleBar_tv_register)
    TextView loginTitleBarTvRegister;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("重置密码");
        setContentView(R.layout.activity_change_pass);
        ButterKnife.bind(this);
//            this.context=context;
//       //不是手机号不让发验证码
//        if (ZhengzeUtils.isChinaPhoneLegal(etUser.getText().toString())){
//            registerTimer.setText("获取验证码");
//            registerTimer.setEnabled(true);
////            Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
//        }else {
//            registerTimer.setText("请输入正确的手机号");
//            registerTimer.setEnabled(false);
//        }
//        buttonChange.setBackgroundResource(R.drawable.btn_bg_pressed);
//        buttonChange.setEnabled(false);
       String username = etUser.getText().toString().trim();
         String password = etPass.getText().toString().trim();
        String repass = etPass2.getText().toString().trim();
        String vacode = etYanzhengma.getText().toString().trim();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(vacode) && (repass.equals(password))) {

            buttonChange.setBackgroundResource(R.drawable.login_btn_bg_selector);
            buttonChange.setEnabled(true);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            });

        } else {
            Toast.makeText(this, "请检查信息填写是否有错", Toast.LENGTH_SHORT).show();
        }

    }

    /***
     * 点击事件发送验证码
     * @param view
     */
    public void changeSMS(View view) {

        if (ZhengzeUtils.isChinaPhoneLegal(etUser.getText().toString())) {//etUser.getText().toString()
            // String vacode=etYanzhengma.getText().toString();//怎么是编辑框？vacode验证码缩写
            Toast.makeText(this, "验证码已发送", Toast.LENGTH_SHORT).show();
            sendSMSHttp(etUser.getText().toString());//把手机号发送到后台
        } else {
//            registerTimer.setText("手机号错误");
            registerTimer.setEnabled(false);//设置不可用
            Toast.makeText(this, "请检查手机号填写是否有错", Toast.LENGTH_SHORT).show();

        }
    }

    /***
     * 提交修改的密码
     */
    @OnClick(R.id.button_change)

    public void onViewClicked() {
        String username = etUser.getText().toString().trim();
        String password = etPass.getText().toString().trim();
        String repass = etPass2.getText().toString().trim();
        String vacode = etYanzhengma.getText().toString().trim();
        String type2 = "2";
        changepassHttp(username, password, "没有邀请码"+password,vacode, type2);//重置密码网路请求
        Toast.makeText(this, "提交请求中", Toast.LENGTH_SHORT).show();


    }

    /***
     * 重置密码请求
     */
    private void changepassHttp(String phoneNumber, String passWord,String invitationCode, String validateCode, String type) {
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
                ChangePassBean bean = new Gson().fromJson(String.valueOf(response), ChangePassBean.class);//
                if (bean.getResultCode() == 0) {
                    //SharedPreferenceUtil.SaveData("changepass", bean.getData() + "");
                    startActivity(new Intent(ChangePassActivity.this, LoginActivity.class));
                    finish();//防止返回键又回来
                } else {
                    Toast.makeText(ChangePassActivity.this, "展示给用户的结果" + bean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                Log.d("mhy", "senfsms" + response);//获取登录结果json
                SendSmsBean bean = new Gson().fromJson(String.valueOf(response), SendSmsBean.class);//
                if (bean.getResultCode() == 0) {
                    SharedPreferenceUtil.SaveData("sendsms", bean.getData() + "");
                } else {
                    Toast.makeText(ChangePassActivity.this, "展示给用户验证码请求的结果" + bean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick({R.id.login_titleBar_iv_back, R.id.login_titleBar_tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_titleBar_iv_back:
                finish();
                break;
            case R.id.login_titleBar_tv_register:
                IntentUtils.openActivity(ChangePassActivity.this,RegisterActivity.class);

                break;
        }
    }
}
