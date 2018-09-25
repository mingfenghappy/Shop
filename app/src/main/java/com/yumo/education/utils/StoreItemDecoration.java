package com.yumo.education.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class StoreItemDecoration extends RecyclerView.ItemDecoration {
    /***
     * 为什么要给RecyclerView设置分割线？
     RecyclerView并没有支持divider这样的属性。那么怎么办，你可以给Item的布局去设置margin，
     当然了这种方式不够优雅，我们文章开始说了，我们可以自由的去定制它，
     当然我们的分割线也是可以定制的，它不像listview可以直接设置设定中界线
     所以一般都给RecyclerView设置分割线
     */
    private int space;
    private int bottomSpace;

    public StoreItemDecoration(int space) {
        this.space = space;
    }

    public StoreItemDecoration(int space, int bottomSpace) {
        this.space = space;
        this.bottomSpace = bottomSpace;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //StaggeredGridLayoutManager随机的宽高GridLayoutManager等高等宽
        StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        int spanIndex = params.getSpanIndex();
        if (spanIndex == 0) {
            outRect.left =30;
        } else {
            outRect.left = space;
        }
        outRect.bottom = bottomSpace;
    }


}