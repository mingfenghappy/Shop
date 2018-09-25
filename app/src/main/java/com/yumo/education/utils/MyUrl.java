package com.yumo.education.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yumo.education.bean.ChangePassBean;
import com.yumo.education.bean.LoginBean;
import com.yumo.education.bean.LoginthridBean;
import com.yumo.education.bean.SendSmsBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 * Created by My on 2018/2/1.
 */

public class MyUrl {
//        public final static String BASE_URL="http://192.168.1.100:8080/Test";//连接地址
    public final static String BASE_URL="http://192.168.0.193:8080/Test";//连接地址
//    public final static String BASE_URL = "http://192.168.43.212:8080/Test";//连接地址
//Connection connect;
//
//    {
//        try {
//            connect = DriverManager.getConnection("jdbc:mysql://192.168.0.193:3306/ykd","root","root");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public final static String login = BASE_URL + "/ykd/login";//登录
    public final static String isbound = BASE_URL + "/ykd/isbound";//绑定
    public final static String loginthrid = BASE_URL + "/ykd/loginthrid";//第三方登录
    public final static String register = BASE_URL + "/ykd/register";//注册
    public final static String sendSMS = BASE_URL + "/ykd/sendSMS";//发送验证码
    public final static String session = BASE_URL + "/ykd/session";//测试session
    public final static String testRedis = BASE_URL + "/ykd/testRedis";//测试Redis
    public final static String homepage = BASE_URL + "/first/homepage";//首页
    public final static String addshoppingcart = BASE_URL + "/first/addshoppingcart";//加入购物车

    public final static String classificationofgoods = BASE_URL + "/first/classificationofgoods";//分类
    public final static String commoditydetails = BASE_URL + "/first/commoditydetails";//商品详情
    public final static String search = BASE_URL + "/first/search";//搜索
    public final static String seeallevaluation = BASE_URL + "/first/seeallevaluation";//获取评论
    public final static String acceptReturn = BASE_URL + "/admin/acceptReturn";//"同意退款"
    public final static String addProduct = BASE_URL + "/admin/addProduct";//addProduct
    public final static String bar = BASE_URL + "/admin/bar";//getBar//7种请求方式
    public final static String changeMoreOrder = BASE_URL + "/admin/changeMoreOrder";//changeMoreOrder
    public final static String changeOrder = BASE_URL + "/admin/changeOrder";//changeOrder

    public final static String deleteMoreOrder = BASE_URL + "/admin/deleteMoreOrder";//deleteMoreOrder
    public final static String deleteMoreProduct = BASE_URL + "/admin/deleteMoreProduct";//删除多个商品
    public final static String deleteOrder = BASE_URL + "/admin/deleteOrder";//deleteOrder
    public final static String deleteProduct = BASE_URL + "/admin/deleteProduct";//删除商品
    public final static String index = BASE_URL + "/admin/index";//getIndex
    public final static String logout = BASE_URL + "/admin/logout";//logout
    public final static String main = BASE_URL + "/admin/main";//getMain
    public final static String menu = BASE_URL + "/admin/menu";//getmenu
    public final static String orderReturnDetail = BASE_URL + "/admin/orderReturnDetail";//退款详情

    public final static String orderReturnList = BASE_URL + "/admin/orderReturnList";//订单退款
    public final static String order_detail = BASE_URL + "/admin/order_detail";//OrderDetail
    public final static String orders = BASE_URL + "/admin/orders";//getFirstPage
    public final static String productList = BASE_URL + "/admin/productList";//商品列表
    public final static String refuseReturn = BASE_URL + "/admin/refuseReturn";//拒绝退款
    public final static String searchOrder = BASE_URL + "/admin/searchOrder";//searchOrder
    public final static String submitProduct = BASE_URL + "/admin/submitProduct";//submitProduct
    public final static String top = BASE_URL + "/admin/top";//getTop
    public final static String aReturnRequest = BASE_URL + "/person/aReturnRequest";//申请退款
    public final static String  aReturnRequestOk= BASE_URL + "/person/admin/aReturnRequest";//同意退款
    public final static String buyorderlist = BASE_URL + "/person/buyorderlist";//我的订单
    public final static String  cancelorderform= BASE_URL + "/person/cancelorderform";//取消、确认收货、删除订单
    public final static String  detailsoftherefund= BASE_URL + "/person/detailsoftherefund";//退款详情
    public final static String  evaluate= BASE_URL + "/person/evaluate";//评价
    public final static String  fileUpload= BASE_URL + "/person/fileUpload";//上传文件
    public final static String  lineitem= BASE_URL + "/person/lineitem";//订单详情
    public final static String personalcenter = BASE_URL + "/person/personalcenter";//个人中心
    public final static String  personaldata= BASE_URL + "/person/personaldata";//个人资料
    public final static String  personaldataupload= BASE_URL + "/person/personaldataupload";//上传资料
    public final static String  reasonforreturn= BASE_URL + "/person/reasonforreturn";//退货原因
    public final static String returnsforrefund = BASE_URL + "/person/returnsforrefund";//退款退货

    public final static String addoreditaddress= BASE_URL + "/shopcard/addoreditaddress";//编辑地址
    public final static String  closeTimer= BASE_URL + "/shopcard/closeTimer";//测试关闭定时器
    public final static String  confirmanorder= BASE_URL + "/shopcard/confirmanorder";//确认订单
    public final static String  deletelocation= BASE_URL + "/shopcard/deletelocation";//删除、设置地址
    public final static String  deleteyourcart= BASE_URL + "/shopcard/deleteyourcart";//删除购物车商品
    public final static String  edityourcart= BASE_URL + "/shopcard/edityourcart";//编辑购物车
    public final static String generateindent = BASE_URL + "/shopcard/generateindent";//立即下单
    public final static String  notifyPay= BASE_URL + "/shopcard/notifyPay";//notifyPay
    public final static String  numberofmessage= BASE_URL + "/shopcard/numberofmessage";//消息数量
    public final static String  payment= BASE_URL + "/shopcard/payment";//支付

    public final static String  sendpush= BASE_URL + "/shopcard/sendpush";//推送消息
    public final static String  shoppingcartlist= BASE_URL + "/shopcard/shoppingcartlist";//获取购物车
    public final static String  startTimer= BASE_URL + "/shopcard/startTimer";//开启定时器
    public final static String  systemMessages= BASE_URL + "/shopcard/systemMessages";//系统消息
    public final static String  takelocationlist= BASE_URL + "/shopcard/takelocationlist";//获取用户地址

//    public final static String  = BASE_URL + "";//

    /***
     * 登录网路请求
     * @param loginuser
     * @param loginpsw
     */
    private void loginHttp(String loginuser, String loginpsw) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", loginuser);
        map.put("pass", loginpsw);
        OkHttpUtils.post().url(MyUrl.login).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("mhy", request + "---------" + e);

            }

            @Override
            public void onResponse(String response) {
                Log.d("mhy", "login" + response);//获取登录结果json
                LoginBean bean = new Gson().fromJson(String.valueOf(response), LoginBean.class);//
                if (bean.getResultCode() == 0) {
                    SharedPreferenceUtil.SaveData("userId", bean.getData().getUserId() + "");
//                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
//                    finish();//防止返回键又回来登录
                } else {
                    //Toast.makeText(LoginActivity.this, "展示给用户的登录结果"+bean.getMsg(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    /***
     * 第三方登录网路请求
     * @param
     * @param
     */
    private void loginthridHttp(String openId, String type) {
        Map<String, String> map = new HashMap<>();
        map.put("openId", openId);
        map.put("type", type);
        OkHttpUtils.post().url(MyUrl.loginthrid).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("mhy", request + "---------" + e);

            }

            @Override
            public void onResponse(String response) {
                Log.d("mhy", "loginthrid" + response);//获取登录结果json
                LoginthridBean bean = new Gson().fromJson(String.valueOf(response), LoginthridBean.class);//
                if (bean.getResultCode() == 0) {
                    SharedPreferenceUtil.SaveData("openId", bean.getData().getOpenId() + "");
//                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
//                    finish();//防止返回键又回来登录

                } else {
                    // Toast.makeText(LoginActivity.this, "展示给用户的登录结果"+bean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /***
     * 重置密码请求
     */
    private void changepassHttp(String phoneNumber, String passWord, String validateCode, String type) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("passWord", passWord);
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
                    SharedPreferenceUtil.SaveData("userId", bean.getData() + "");
//                    startActivity(new Intent(ChangePassActivity.this, LoginActivity.class));
//                    finish();//防止返回键又回来
                } else {
//                    Toast.makeText(ChangePassActivity.this, "展示给用户的结果" + bean.getMsg(), Toast.LENGTH_SHORT).show();
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
                Log.d("mhy", "register" + response);//获取登录结果json
                SendSmsBean bean = new Gson().fromJson(String.valueOf(response), SendSmsBean.class);//
                if (bean.getResultCode() == 0) {
                    SharedPreferenceUtil.SaveData("userId", bean.getData() + "");
                } else {
//                    Toast.makeText(ChangePassActivity.this, "展示给用户验证码请求的结果" + bean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void testGetHttp() {
        Map<String, String> map = new HashMap<>();
        map.put("", "");
        OkHttpUtils.get().url("" + MyUrl.BASE_URL).params(map).tag(this).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("mhy", "------" + e);
            }

            @Override
            public void onResponse(String response) {
                Log.d("mhy", "" + response);
            }
        });
    }
}
