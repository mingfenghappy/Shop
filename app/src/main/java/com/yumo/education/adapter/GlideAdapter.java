package com.yumo.education.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yumo.education.R;

import java.util.ArrayList;
import java.util.List;

public class GlideAdapter extends RecyclerView.Adapter<GlideAdapter.ViewHoldr> {
    Context context;
    List<String> list;
    private List<Integer> mHeights;

    public GlideAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        getRandomHeight();//pubuliu瀑布流

    }


    @Override
    public ViewHoldr onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_glide_item, viewGroup, false);
        ViewHoldr viewHoldr = new ViewHoldr(view);
        return viewHoldr;
    }

    @Override
    public void onBindViewHolder(ViewHoldr viewHoldr, final int i) {
        viewHoldr.adapterTv.setText(list.get(i));
        //瀑布流
        ViewGroup.LayoutParams layoutParams = viewHoldr.itemView.getLayoutParams();
        layoutParams.height = mHeights.get(i);
        //layoutParams.width=mHeights.get(i);

        viewHoldr.itemView.setLayoutParams(layoutParams);

        viewHoldr.adapterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
viewHoldr.adapterIv.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
    }

    public void getRandomHeight() {
        mHeights = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            mHeights.add((int) (300 + Math.random() * 400));
        }//瀑布流随机数
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoldr extends RecyclerView.ViewHolder {
        TextView adapterTv;
        ImageView adapterIv;

        public ViewHoldr(View itemView) {
            super(itemView);
//            adapterTv = itemView.findViewById(R.id.adapter_my_tv);
//            adapterIv = itemView.findViewById(R.id.adapter_iv);
        }
    }

}
