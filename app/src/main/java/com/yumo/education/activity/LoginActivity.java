package com.yumo.education.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.yumo.education.R;
import com.yumo.education.bean.LoginBean;
import com.yumo.education.bean.LoginthridBean;
import com.yumo.education.utils.CheckEditForButton;
import com.yumo.education.utils.EditTextChangeListener;
import com.yumo.education.utils.MyUrl;

import com.yumo.education.utils.SharedPreferenceUtil;
import com.yumo.education.utils.ToastUtils;
import com.yumo.education.utils.ZhengzeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final String APP_ID = "1107851786";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    String getopenid;
    @BindView(R.id.user)
    EditText user;
    @BindView(R.id.psw)
    EditText psw;
    @BindView(R.id.checkBox0)
    CheckBox checkBox0;
    @BindView(R.id.checkBox1)
    CheckBox checkBox1;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.zhuce)
    TextView zhuce;
    @BindView(R.id.wangjimima)
    TextView wangjimima;
    @BindView(R.id.qq)
    ImageView qq;
    @BindView(R.id.weixin)
    ImageView weixin;
    @BindView(R.id.zhifubao)
    ImageView zhifubao;
    @BindView(R.id.renlian)
    ImageView renlian;
    @BindView(R.id.zhiwen)
    ImageView zhiwen;
    @BindView(R.id.denglu)
    LinearLayout denglu;

    private SharedPreferences mSpSettings=null;//声明一个sharedPreferences用于保存数据
    private static final String PREPS_NAME="NamePsw";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("登录");
       // requestWindowFeature(Window.FEATURE_NO_TITLE);//设置隐藏标题栏
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
         //单选框显示密码
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    psw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    psw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                //光标在末尾显示
                psw.setSelection(psw.length());
            }
        });

         //编辑框无内容按钮不可用
        //1.创建工具类对象 设置监听空间
        CheckEditForButton checkEditForButton = new CheckEditForButton(login);
        //2.把所有被监听的EditText设置进去
        checkEditForButton.addEditText(user, psw);
        //3.根据接口回调的方法,分别进行操作
        checkEditForButton.setListener(new EditTextChangeListener() {
            @Override
            public void allHasContent(boolean isHasContent) {
                if (isHasContent) {
                    login.setTextColor(Color.parseColor("#FF148F"));

                } else {
                    login.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        //记住密码
        checkBox0= (CheckBox) findViewById(R.id.checkBox0);
      //  checkBox0.setChecked(true);//设置默认记住密码
        checkBox0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ApplySharedPref")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，记住密码
                    mSpSettings =getSharedPreferences(PREPS_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor edit= mSpSettings.edit();//得到Editor对象
                    edit.putBoolean("isKeep", true);//记录保存标记
                    edit.putString("phone", user.getText().toString());//记录用户名
                    edit.putString("pass", psw.getText().toString());//记录密码
                    edit.commit();//**提交
//                    edit.commit();//**提交
                }
                if (!isChecked){
                    //否则不计密码
                    mSpSettings =getSharedPreferences(PREPS_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor edit= mSpSettings.edit();
                    edit.putBoolean("isKeep", true);//保存的文件名isKeep
                    edit.putString("phone", user.getText().toString());
                    edit.putString("pass", "");
                    edit.commit();
//                    edit.commit();
                }
                //跳转
//                Intent intent=new Intent(LoginActivity.this, LoginShowActivity.class);
//                startActivity(intent);
            }
        });

        String loginuser = user.getText().toString();
        String loginpsw=psw.getText().toString();
        //登录按钮设置不可用
        login.setEnabled(false);
        login.setBackgroundResource(R.drawable.btn_bg_pressed);
        //                login.setEnabled(false);//登录按钮设置不可用
        if (TextUtils.isEmpty(loginuser)){
            Toast.makeText(this, "请输手机号", Toast.LENGTH_SHORT).show();
            // return;
        }
        if (ZhengzeUtils.isChinaPhoneLegal(loginuser)){
            //是手机号才能继续进行

//                    if (TextUtils.isEmpty(loginpsw)){
//                        Toast.makeText(this, "请输入6-20位字母数字组合密码", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
            if (ZhengzeUtils.isMima(loginpsw)) {
                login.setEnabled(true);
                login.setBackgroundResource(R.drawable.login_btn_bg_selector);

                // loginHttp(user.getText().toString(),psw.getText().toString());//登录网路请求
            }else
            {
                Toast.makeText(this, "请输入6-20位字母数字组合密码", Toast.LENGTH_SHORT).show();

            }//break;
        }else {
            Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
            // return;

        }

//传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID,LoginActivity.this.getApplicationContext());


    }
    @Override
    protected void onResume() {
        super.onResume();
        getData();//在界面显示数据之前得到之前存储的数据
    }

    private void getData() {
        mSpSettings=getSharedPreferences(PREPS_NAME, MODE_PRIVATE);
        if(mSpSettings.getBoolean("isKeep", false)){
            //如果之前存储过,则显示在相应文本框内
            user.setText(mSpSettings.getString("phone", ""));
            psw.setText(mSpSettings.getString("pass", ""));
        }else{//否则显示为空
            user.setText("");
            psw.setText("");
        }
    }
    @OnClick({R.id.login, R.id.zhuce, R.id.wangjimima, R.id.qq, R.id.weixin, R.id.zhifubao, R.id.renlian, R.id.zhiwen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login://登录

                String loginuser = user.getText().toString();
                String loginpsw=psw.getText().toString();
                loginHttp(loginuser, loginpsw);//登录网路请求

                break;
            case R.id.zhuce://注册
                Toast.makeText(this, "开始注册", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                //  finish();//防止返回键又回来
                break;
            case R.id.wangjimima://忘记密码
                Toast.makeText(this, "帮您找回密码", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,ChangePassActivity.class));
                break;
            case R.id.qq://qq
                Toast.makeText(this, "启动QQ", Toast.LENGTH_SHORT).show();
                //进行授权登录之后 跳转绑定
                /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
                 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
                 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(LoginActivity.this,"all", mIUiListener);
                if (openID!=null){          //传值给Bound
                Intent i= new Intent(LoginActivity.this,BoundActivity.class);
                i.putExtra("openId",openID);
                // i.putExtra("openId",getopenid);
                startActivity(i);//这里只是传值

                loginthridHttp(openID,1); }
                // startActivity(new Intent(LoginActivity.this,BoundActivity.class));
               // finish();//防止返回键又回来登录
                break;
            case R.id.weixin:
                Toast.makeText(this, "启动微信", Toast.LENGTH_SHORT).show();
                //进行授权登录之后 跳转绑定
                final String openId = user.getText().toString().trim();//不可用
                ////////

                ///////
                int type2 = 2;
                if (!TextUtils.isEmpty(openId)) {
                    Toast.makeText(this, "提交请求中", Toast.LENGTH_SHORT).show();
                    //先微信登录获取用户openid 在提交openID给后台
                    loginthridHttp(openId,type2);//重置密码网路请求
                    startActivity(new Intent(LoginActivity.this,BoundActivity.class));
                } else {
                    Toast.makeText(this, "请检查信息填写是否有错", Toast.LENGTH_SHORT).show();
                }

              //
                startActivity(new Intent(LoginActivity.this,BoundActivity.class));
                finish();//防止返回键又回来登录
                break;
            case R.id.zhifubao://支付宝
                Toast.makeText(this, "启动支付宝", Toast.LENGTH_SHORT).show();
                break;
            case R.id.renlian://人脸
                Toast.makeText(this, "人脸识别", Toast.LENGTH_SHORT).show();
                break;
            case R.id.zhiwen://指纹
                Toast.makeText(this, "指纹识别", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    /***
     * 登录网路请求
     * @param loginuser
     * @param loginpsw
     */
    private void loginHttp(String loginuser,String loginpsw) {
        Map<String,String> map=new HashMap<>();
        map.put("phone",loginuser);
        map.put("pass",loginpsw);
        OkHttpUtils.post().url(MyUrl.login).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("mhy",request+"异常："+e);//request+
              //
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(LoginActivity.this, "登录异常", Toast.LENGTH_SHORT).show();
                   }
               });
            }
            @Override
            public void onResponse(String response) {
                Log.d("mhy","login"+response);//获取登录结果json
                LoginBean bean=new Gson().fromJson(String.valueOf(response),LoginBean.class);//
                if (bean.getResultCode()==0){
                    SharedPreferenceUtil.SaveData("userId",bean.getData().getUserId()+"");
                    ToastUtils.show(LoginActivity.this,"展示给用户的登录结果"+bean.getMsg());
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();//防止返回键又回来登录
                }else {
                    Toast.makeText(LoginActivity.this, "展示给用户的登录结果"+bean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /***
     * 第三方登录网路请求
     * @param
     * @param
     */
    private void loginthridHttp(String openId,int type) {
        Map<String,String>map=new HashMap<>();
        map.put("openId",openId);
        map.put("type", "1");//1qq,2weixin

        OkHttpUtils.post().url(MyUrl.loginthrid).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("mhy",request+"---------"+e);
            }
            @Override
            public void onResponse(String response) {
                Log.d("mhy","login3"+response);//获取登录结果json
                LoginthridBean bean=new Gson().fromJson(String.valueOf(response),LoginthridBean.class);//
                if (bean.getResultCode()==0){
                  //  SharedPreferenceUtil.SaveData("openId",bean.getData().getOpenId()+"");
               //   getopenid =bean.getData().getOpenId()+"";
               //   Log.d("mhy",""+bean.getData().getOpenId());


                      startActivity(new Intent(LoginActivity.this,BoundActivity.class));
                    finish();//防止返回键又回来登录
                }else {
                    Toast.makeText(LoginActivity.this, "展示给用户的登录结果"+bean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    String openID;
    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
               // String
                        openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e("mhy"+TAG,"登录成功"+response.toString());
                        Log.d("mhy","openId:"+openID);
                    }
                    @Override
                    public void onError(UiError uiError) {
                        Log.e("mhy"+TAG,"登录失败"+uiError.toString());
                    }
                    @Override
                    public void onCancel() {
                        Log.e("mhy"+TAG,"登录取消");
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
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
                LoginActivity.this.finish();
                System.exit(0);
            }
        }).show();
    }

}
