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
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yumo.education.R;
import com.yumo.education.activity.BoundActivity;
import com.yumo.education.activity.MainActivity;
import com.yumo.education.bean.BoundBean;
import com.yumo.education.bean.SendSmsBean;
import com.yumo.education.utils.MyUrl;
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
public class BoundAccountFragment extends Fragment {
    String openID;
    int userID;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.button_change)
    Button buttonChange;
    Unbinder unbinder;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        openID = ((BoundActivity) activity).getTitles();
    }

    //通过强转成宿主activity，就可以获取到传递过来的数据
    public BoundAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bound_account, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /***
     * 发送验证码
     * @param view
     */
    public void changeSMS(View view) {

        if (ZhengzeUtils.isChinaPhoneLegal(String.valueOf(etUser))) {//etUser.getText().toString()
            String vacode = "111111";
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

    /***
     * 绑定账号请求
     */

    private void isboundHttp(String phoneNumber, String passWord, String type, String openId) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("passWord", passWord);

        map.put("type", type);
        map.put("openId", openId);
       // map.put("userId", String.valueOf(userId));


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
                    userID = bean.getData().getUserId();

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button_change)
    public void onViewClicked() {
        isboundHttp(etUser.getText().toString(),etPass.getText().toString(),"1",openID);
    }
}
