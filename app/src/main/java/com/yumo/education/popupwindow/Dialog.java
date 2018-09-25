package com.yumo.education.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yumo.education.R;

/**
 * 类描述：提示框
 * 作  者：蔡佳彬
 * 时  间：2017-07-04
 * 修改备注：
 */
public class Dialog extends PopupWindow {
    Context context;

    public Dialog(Context context, String confirm, String msg, final setOnDialogClickListener onDialogClick) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pw_dialog, null);
        TextView textViewMsg = (TextView) view.findViewById(R.id.pw_dialog_msg);
        textViewMsg.setText(msg);
        TextView textViewConfirm = (TextView) view.findViewById(R.id.pw_dialog_confirm);
        textViewConfirm.setText(confirm);

        textViewConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                onDialogClick.onClick(v);
            }
        });//确定
        view.findViewById(R.id.pw_dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x50000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.pupopWindowAnimation);
        this.showAsDropDown(view);//展示
    }

    public interface setOnDialogClickListener {
        void onClick(View v);
    }
}
