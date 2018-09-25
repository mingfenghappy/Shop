package com.yumo.education.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yumo.education.R;
import com.yumo.education.adapter.GuideAdapter;
import com.yumo.education.adapter.ViewPageAdapter;
import com.yumo.education.utils.IntentUtils;
import com.yumo.education.utils.OnItemClickListener;
import com.yumo.education.utils.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：引导页面
 * 作  者：蔡佳彬
 * 时  间：2017-06-23
 * 修改备注：
 */
public class GuideActivity extends BaseActivity {
    Context context;
    @BindView(R.id.viewPager_guide)
    ViewPager viewPager_guide;
    @BindView(R.id.layout_guide)
    LinearLayout layout_guide;

    private List<View> list;
//    GuideAdapter guideAdapter;

    private ViewPageAdapter mPageAdapter;
    private Button mBtnStart;

//    ImageView[] dots = null;
//    private int[] image = new int[]{R.color.white, R.color.themeColor, R.color.colorAccent, R.color.colorPrimaryDark};
//    int onSlide = -1;
private int[] imgRes = new int[]
        {R.mipmap.guide_1,R.mipmap.guide_2, R.mipmap.guide_3,R.mipmap.guide_4};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        ButterKnife.bind(this);
        SetTranslanteBar();
        list = new ArrayList<>();
//        guideAdapter = new GuideAdapter(list, image);
//        for (int i = 0; i < image.length; i++) {
//            ImageView img = new ImageView(this);
//            list.add(img);
//        }
//        viewPager_guide.setAdapter(guideAdapter);
//        viewPager_guide.setCurrentItem(0);
//        viewPager_guide.setOffscreenPageLimit(1);
//
//        infoDots();
//
//        guideAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                if (position != 0 && position % (list.size() - 1) == 0) {
//                    SharedPreferenceUtil.SaveData("isFirst", true);
//                    startActivity(new Intent(context, MainActivity.class));
//                    finish();
//                }
//            }
//        });
//
//        viewPager_guide.addOnPageChangeListener(new OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                onSlide = position;
//                for (int i = 0; i < dots.length; i++) {
//                    dots[i].setEnabled(true);
//                }
//                dots[position].setEnabled(false);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });//
        //
        initDatas();
        initViews();
    }

    private void initDatas() {
        for (int i = 0; i < imgRes.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.item_guide,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.item_guide_bg);
            imageView.setBackgroundResource(imgRes[i]);
            list.add(view);
        }
        mPageAdapter = new ViewPageAdapter(list);
    }

    private void initViews() {
        viewPager_guide = (ViewPager) findViewById(R.id.viewPager_guide);
        viewPager_guide.setAdapter(mPageAdapter);
        viewPager_guide.addOnPageChangeListener(new MyPageChangeListener());
        mBtnStart = (Button) findViewById(R.id.guide_btn_start);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(GuideActivity.this,MainActivity.class));
                IntentUtils.openActivity(context,MainActivity.class);
                finish();
            }
        });
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == list.size()-1) {
                mBtnStart.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(GuideActivity.this,R.anim.anim_guide_btn_start);
                mBtnStart.startAnimation(animation);
            } else {
                mBtnStart.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }//
    }

    /**
     * 设置底部圆点
     */
//    private void infoDots() {
//        dots = new ImageView[image.length];
//        for (int i = 0; i < dots.length; i++) {
//            ImageView imageView = new ImageView(this);
//            imageView.setLayoutParams(new LayoutParams(30, 30));// 设置图片大小
//            imageView.setPadding(0, 0, 20, 0);// 设置图片间距
//            imageView.setImageResource(R.drawable.banner_dot);
//
//            layout_guide.addView(imageView);
//
//            dots[i] = imageView;
//            dots[i].setEnabled(true);
//            dots[i].setTag(i);
//            dots[i].setOnClickListener(new OnClickListener() {
//                public void onClick(View v) {
//                    viewPager_guide.setCurrentItem((Integer) v.getTag());
//                }
//            });
//        }
//        dots[0].setEnabled(false);
//    }

    @Override
    public int setBaseView() {
        return R.layout.activity_guide;
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
