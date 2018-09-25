package com.yumo.education.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yumo.education.R;
import com.yumo.education.utils.StatusBarCompat;
import com.yumo.education.utils.UtilBox;

public abstract class BaseActivity extends FragmentActivity {
    private ImageView base_activity_back, base_activity_more;
    private FrameLayout base_activity_father_view;
    private TextView base_activity_title_text;
    private RelativeLayout base_activity_title;
    private TextView base_activity_moretext;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        UtilBox.Log("BaseActivity" + this.getClass().getName());
        mContext = this;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//默认不弹出键盘
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        //沉浸状态栏
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.statusBar));
        //注入界面文件
        initView();
    }

    @Override
    public void onBackPressed() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        finish();
    }

    private void initView() {
        base_activity_moretext = (TextView) findViewById(R.id.base_activity_moretext);
        base_activity_back = (ImageView) findViewById(R.id.base_activity_back);
        base_activity_father_view = (FrameLayout) findViewById(R.id.base_activity_father_view);
        base_activity_more = (ImageView) findViewById(R.id.base_activity_more);
        base_activity_title = (RelativeLayout) findViewById(R.id.base_activity_title);
        base_activity_title_text = (TextView) findViewById(R.id.base_activity_title_text);
        base_activity_father_view.addView(View.inflate(this, setBaseView(), null));
        if (showTitle() == false) {
            base_activity_title.setVisibility(View.GONE);
        } else {
            base_activity_title.setVisibility(View.VISIBLE);
        }
        base_activity_title_text.setText(setTitleText());
        base_activity_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backListener();
            }
        });
        base_activity_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moreImgListener();
            }
        });
        base_activity_moretext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moretextListener();
            }
        });
    }

    /**
     * 返回按钮点击事件
     */
    public void backListener() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        finish();
    }

    /**
     * 右侧图片点击事件
     */
    public void moreImgListener() {
    }

    /**
     * 右侧文字点击事件
     */
    public void moretextListener() {
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        base_activity_title_text.setText(title);
    }

    /**
     * 右侧按钮显示图片
     *
     * @param drawable
     */
    public void setMoreImg(int drawable) {
        base_activity_moretext.setVisibility(View.GONE);
        base_activity_more.setVisibility(View.VISIBLE);
        base_activity_more.setImageResource(drawable);
    }

    /**
     * 右侧按钮显示文字
     *
     * @param more
     */
    public void setMoreText(String more) {
        base_activity_moretext.setVisibility(View.VISIBLE);
        base_activity_more.setVisibility(View.GONE);
        base_activity_moretext.setText(more);
    }

    /**
     * 右侧按钮显示图片,文字
     *
     * @param drawable
     * @param more
     */
    public void setMoreImgText(int drawable, String more) {
        base_activity_moretext.setVisibility(View.VISIBLE);
        base_activity_more.setVisibility(View.VISIBLE);
        base_activity_more.setImageResource(drawable);
        base_activity_moretext.setText(more);
    }

    /**
     * 设置界面主体
     *
     * @return
     */
    public abstract int setBaseView();

    /**
     * 设置标题是否显示
     *
     * @return
     */
    public abstract boolean showTitle();

    /**
     * 设置标题文字
     *
     * @return
     */
    public abstract String setTitleText();

    /**
     * 设置主体背景颜色
     *
     * @param baseColor
     */
    public void setBaseBack(String baseColor) {
        base_activity_father_view.setBackgroundColor(Color.parseColor(baseColor));
    }

    /**
     * 设置是否显示返回按钮
     *
     * @param showBack
     */
    public void showBack(boolean showBack) {
        if (showBack == true)
            base_activity_back.setVisibility(View.VISIBLE);
        else
            base_activity_back.setVisibility(View.GONE);
    }

    /**
     * 根据资源文件设置title文字颜色
     *
     * @param id
     */
    public void setTitleTextColor(int id) {
        base_activity_title_text.setTextColor(getResources().getColor(id));
    }

    /**
     * 利用资源Id设置顶部标题背景颜色
     *
     * @param id
     */
    public void setTitleBackground(int id) {
        base_activity_title.setBackgroundColor(id);
    }

    /**
     * 设置顶部标题背景图片
     * 会覆盖背景颜色
     *
     * @param id
     */
    public void setTitleBackgroundImg(int id) {
        base_activity_title.setBackgroundResource(id);
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, color));
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }

}
