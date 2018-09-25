package com.yumo.education.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yumo.education.R;
import com.yumo.education.activity.GuideActivity;
import com.yumo.education.activity.MainActivity;
import com.yumo.education.utils.SharedPreferenceUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @项目名: AppLaunchVideoDemo
 * @包名: fme.net.iusky.yijiayou.applaunchvideodemo
 * @作者: zyp
 * @创建时间: 2017/11/09 17:11
 * @描述:
 */
public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    CustomVideoView videoView;
    TextView next;
    private Handler mHandler = new Handler();
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public  void handleMessage(Message msg){
            boolean isFirst = SharedPreferenceUtil.getBoolean(SplashActivity.this,"isFirst",true);
            if (isFirst) {
                SharedPreferenceUtil.putBoolean(SplashActivity.this,"isFirst",false);
                startActivity(new Intent(SplashActivity.this,GuideActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
            finish();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);



        videoView = (CustomVideoView) findViewById(R.id.videoView);
        next = (TextView) findViewById(R.id.to_next);
        next.setOnClickListener(this);

//        videoView.setVideoURI(Uri.parse("http://v.baidu.com/link?url=dm_00pw_klemzFaU2vO4w7zo2Cc1yuX_dCEHtgd-yB9KwhC-tqPQIQDpFgkC10KY9_vx0lGa5FPle84ZncItjvPa9yO3tHp3fyp_6-TSLDp42NxrynQyx0g..&page=videoMultiNeed"));
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.media));

        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                finish();
                Timer timer=new Timer();
                TimerTask task=new TimerTask() {
                    @Override
                    public void run() {
                        Message msg=handler.obtainMessage();
                        handler.sendMessage(msg);
                    }
                };
                timer.schedule(task,10000);//秒

            }
        });
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                boolean isFirst = SharedPreferenceUtil.getBoolean(SplashActivity.this,"isFirst",true);
//                if (isFirst) {
//                    SharedPreferenceUtil.putBoolean(SplashActivity.this,"isFirst",false);
//                    startActivity(new Intent(SplashActivity.this,GuideActivity.class));
//                } else {
//                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
//                }
//                finish();
//            }
//        },2000);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.to_next:
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
}
