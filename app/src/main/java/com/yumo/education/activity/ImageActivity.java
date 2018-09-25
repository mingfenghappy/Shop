package com.yumo.education.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.yumo.education.R;

public class ImageActivity extends AppCompatActivity {

    public static final String IMAGE_TAG = "imageTag";
    ImageView mimageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        //设置一个ImageView 接受传过来的值
        mimageView = (ImageView) findViewById(R.id.image_view);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString(IMAGE_TAG);
        //Glide蒋传过来的图片网址，加载出来
        Glide.with(this).load(url).into(mimageView);
    }
}