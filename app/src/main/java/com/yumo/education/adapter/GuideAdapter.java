package com.yumo.education.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.yumo.education.utils.OnItemClickListener;

import java.util.List;

/**
 * 类描述：引导页-适配器
 * 作  者：蔡佳彬
 * 时  间：2017-06-13
 * 修改备注：
 */
public class GuideAdapter extends PagerAdapter {
    List<View> list;
    int[] image;

    public GuideAdapter(List<View> list, int[] image) {
        this.list = list;
        this.image = image;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = list.get(position);
        final int count = getCount();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        view.setBackgroundResource(image[position % count]);
        view.setLayoutParams(params);
        container.addView(view, 0);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v, position);
            }
        });
        return list.get(position);
    }

    OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
