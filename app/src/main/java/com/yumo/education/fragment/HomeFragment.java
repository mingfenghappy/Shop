package com.yumo.education.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.yumo.education.R;
import com.yumo.education.activity.LoginActivity;
import com.yumo.education.activity.MainActivity;
import com.yumo.education.activity.XiaoXiActivity;
import com.yumo.education.bean.HomepageBean;
import com.yumo.education.bean.LoginBean;
import com.yumo.education.utils.Glide.GlideUtil;
import com.yumo.education.utils.MyUrl;
import com.yumo.education.utils.SharedPreferenceUtil;
import com.yumo.education.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import butterknife.OnClick;

public class HomeFragment extends Fragment {
    FrameLayout flFone;
    String url1, url2, url3;
    String[]imageUrllist;
    private Banner banner;
    private List<String> urllist;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;
    ConvenientBanner banimg;
    Context context;
//    RecyclerView recyclerView;
//    private List<String> list;//添加item
//    private String[] data = {"北京", "上海", "广州", "深圳", "青海", "合肥", "宿州", "苏州", "盐城", "南海",
//            "南京", "毕加索", "美国", "天津", "湖南", "浙江", "内蒙古", "爱迪生", "爱琴海",
//            "泗洪", "海南", "哈尔滨", "淮安", "黑龙江", "丽江",};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View thisView = inflater.inflate(R.layout.fragment_home, container, false);
        list_path = new ArrayList<>();
        list_title = new ArrayList<>();
        banner = (Banner) thisView.findViewById(R.id.banner);
        flFone = (FrameLayout) thisView.findViewById(R.id.fl_fone);
     //   banimg= (ConvenientBanner) thisView.findViewById(R.id.ban_img);
      //  banimg.startTurning(2000);//2秒自动翻页
        urllist=new ArrayList<>();
        //把数组中的数据添加到集合中去
//        for (int i = 0; i < imageUrllist.length; i++)
//        { list_path.add(imageUrllist[i]);
//           // urllist.add(imageUrllist[i]);
//        }
       // Glide.with(this).load(urllist).into();
        list_path.add(MyUrl.BASE_URL+"/images/slid1.png");
        list_path.add(MyUrl.BASE_URL+"/images/slid2.png");
        list_path.add(MyUrl.BASE_URL+"/images/slid3.png");

        list_title.add("1");
        list_title.add("2");
        list_title.add("3");

        //添加图片
//        recyclerView = (RecyclerView) thisView.findViewById(R.id.home_recycler);
//        gridlayout= (GridLayout) thisView.findViewById(R.id.gridlayout);
        //设置布局管理器（布局风格）
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        //2竖行的布局 就像两个并排的listview
    //    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new MyLoader());
        banner.setBannerAnimation(Transformer.Default);
        banner.setBannerTitles(list_title);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(list_path)
                .start();

        return thisView;

    }
    /**
     * 网络加载图片
     * 使用了Glide图片加载框架
     */
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load((String) path)
                    .into(imageView);
        }
    }
    @OnClick({R.id.home_iv_back, R.id.searchView, R.id.home_ib_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_iv_back:
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                System.exit(0);// 使虚拟机停止运行并退出程序
                break;
            case R.id.searchView:
                //还有回车键
//设置帧布局可用出现搜索结果还得判断结果是否有东西没有就设置列表显提示未搜到
                flFone.setVisibility(View.VISIBLE);

                break;
            case R.id.home_ib_info:
                startActivity(new Intent(getActivity(), XiaoXiActivity.class));
                break;

        }
    }
/***
 *
 * 获取首页请求
 */
private void homepageHttp(String page,String rows) {
    Map<String,String> map=new HashMap<>();
    map.put("page",page);
    map.put("rows",rows);
    OkHttpUtils.post().url(MyUrl.homepage).params(map).build().execute(new StringCallback() {
        @Override
        public void onError(Request request, Exception e) {
            Log.d("mhy",request+"异常："+e);//request+
            //
       Toast.makeText(getActivity(), "请求异常", Toast.LENGTH_SHORT).show();

        }
        @Override
        public void onResponse(String response) {
            Log.d("mhy","homepage"+response);//获取结果json
            HomepageBean bean=new Gson().fromJson(response,HomepageBean.class);//
            if (bean.getResultCode()==0){

//                SharedPreferenceUtil.SaveData("slideShowUrlList",bean.getData().getSlideShowUrlList()+"");//bean.getData()
//                SharedPreferenceUtil.SaveData("classifyList",bean.getData().getClassifyList()+"");//bean.getData()
//                SharedPreferenceUtil.SaveData("recommendationList",bean.getData().getRecommendationList()+"");//bean.getData()

                String[] imageUrl1 = {String.valueOf(bean.getData().getSlideShowUrlList())};
                imageUrllist=imageUrl1;
                url1 = imageUrl1[0];
                url2 = imageUrl1[1];
                url3 = imageUrl1[2];
                ToastUtils.show(getActivity(),"展示给用户的结果"+bean.getMsg());
//                startActivity(new Intent(getActivity(),MainActivity.class));
               // getActivity().finish();//防止返回键又回来登录
            }else {
                Toast.makeText(getActivity(), "展示给用户的登录结果"+bean.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }
    });
}

}
