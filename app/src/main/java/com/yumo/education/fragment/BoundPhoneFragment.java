package com.yumo.education.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yumo.education.R;
import com.yumo.education.activity.BoundActivity;
import com.yumo.education.activity.MainActivity;
import com.yumo.education.bean.BoundBean;
import com.yumo.education.bean.SendSmsBean;
import com.yumo.education.utils.DiyView.BackTimeView;
import com.yumo.education.utils.MyUrl;
import com.yumo.education.utils.SharedPreferenceUtil;
import com.yumo.education.utils.ZhengzeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoundPhoneFragment extends Fragment {


    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_yanzhengma)
    EditText etYanzhengma;
    @BindView(R.id.register_timer)
    BackTimeView registerTimer;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.et_pass)
    EditText et_pass;
    @BindView(R.id.tv_tip2)
    TextView tvTip2;
    @BindView(R.id.et_pass2)
    EditText etPass2;
    @BindView(R.id.button_change)
    Button buttonChange;
    Unbinder unbinder;
    String openID;
    int userID;

    public BoundPhoneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
       openID= ((BoundActivity) activity).getTitles();
    }
//通过强转成宿主activity，就可以获取到传递过来的数据

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bound_phone, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /***
     * 发送验证码
     * @param view
     */
    public void changeSMS(View view) {

        if (ZhengzeUtils.isChinaPhoneLegal(String.valueOf(etUser))) {//etUser.getText().toString()
            String vacode = etYanzhengma.getText().toString();
            //   Toast.makeText(this, "验证码已发送", Toast.LENGTH_SHORT).show();
            sendSMSHttp(etUser.getText().toString());//请求
        } else {
            //  Toast.makeText(this, "请检查手机号填写是否有错", Toast.LENGTH_SHORT).show();

        }
    }

    /***
     * 发送验证码后台请求
     */
    private void sendSMSHttp(String phone) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        OkHttpUtils.post().url(MyUrl.BASE_URL + MyUrl.sendSMS).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("mhy", request + "---------" + e);

            }

            @Override
            public void onResponse(String response) {
                Log.d("mhy", "register" + response);//获取登录结果json
                SendSmsBean bean = new Gson().fromJson(String.valueOf(response), SendSmsBean.class);//
                if (bean.getResultCode() == 0) {
                    //SharedPreferenceUtil.SaveData("userId", bean.getData() + "");
                } else {
                    // Toast.makeText(BoundPhoneFragment.this, "展示给用户验证码请求的结果" + bean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.register_timer, R.id.button_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_timer:
                break;
            case R.id.button_change:
                isboundHttp(etUser.getText().toString().trim(), et_pass.getText().toString().trim(), "yqm", etYanzhengma.getText().toString().trim(), "0",openID);//,userID

                break;
        }
    }
    /***
     * 绑定新创手机账号请求
     */

    private void isboundHttp(String phoneNumber, String passWord, String invitationCode, String validateCode, String type, String openId  ) {//int userId
        Map<String, String> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("passWord", passWord);
        map.put("invitationCode", invitationCode);
        map.put("validateCode", validateCode);
        map.put("type", type);
        map.put("openId", openId);
        //map.put("userId", String.valueOf(userId));


        OkHttpUtils.post().url(MyUrl.register).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("mhy", request + "---------" + e);

            }

            @Override
            public void onResponse(String response) {
                Log.d("mhy", "register" + response);//获取登录结果json
                BoundBean bean = new Gson().fromJson(String.valueOf(response), BoundBean.class);//
                if (bean.getResultCode() == 0) {
                    //   SharedPreferenceUtil.SaveData("register", bean.getData()+ "");
                  userID=  bean.getData().getUserId();
                    Toast.makeText(getActivity(), "展示给用户的结果" + bean.getMsg(), Toast.LENGTH_SHORT).show();
                    //绑定完成去主页
                    startActivity(new Intent(getActivity(), MainActivity.class));
                  getActivity().finish();//防止返回键又回来登录

                } else {
                    Toast.makeText(getActivity(), "结果" + bean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//
//    /***
//     * 发送验证码后台请求
//     */
//    private void sendSMSHttp(String phone) {
//        Map<String, String> map = new HashMap<>();
//        map.put("phone", phone);
//        OkHttpUtils.post().url(MyUrl.sendSMS).params(map).build().execute(new StringCallback() {
//            @Override
//            public void onError(Request request, Exception e) {
//                Log.d("mhy", request + "---------" + e);
//
//            }
//
//            @Override
//            public void onResponse(String response) {
//                Log.d("mhy", "sendsms" + response);//获取登录结果json
//                SendSmsBean bean = new Gson().fromJson(String.valueOf(response), SendSmsBean.class);//
//                if (bean.getResultCode() == 0) {
//                    //  SharedPreferenceUtil.SaveData("sendsms", bean.getData() + "");
//                    Toast.makeText(BoundActivity.this, "验证码已发送" + bean.getMsg(), Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(BoundActivity.this, "请求的结果:" + bean.getMsg(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

}
