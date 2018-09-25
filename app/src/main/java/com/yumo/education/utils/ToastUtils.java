package com.yumo.education.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yumo.education.R;

public class ToastUtils {
	private static Toast toast = null;
	private static View view;
	private static TextView textView_toast;

	private Toast mToast;
	private static ToastUtils mToastUtils;

	private static Toast longToast;
	//不管我们触发多少次Toast调用，都只会持续一次Toast显示的时长，这也算是一个小技巧
 public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                         content, 
                         Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
	//ToastUtil.showToast(context, "内容");
	
	public static void showToast(Context context, String message) {
		if (toast == null) {
			toast = new Toast(context);
			toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 150);
			toast.setDuration(Toast.LENGTH_SHORT);
			view = LayoutInflater.from(context).inflate(R.layout.item_toast, null);
			textView_toast = (TextView) view.findViewById(R.id.textView_toast);
			textView_toast.setText(message);
		} else {
			textView_toast.setText(message);
		}
		toast.setView(view);
		toast.show();
	}/////

	public static void show(Context context, CharSequence text) {
		if (toast == null) {
			toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
		}
		toast.setText(text);
		toast.show();
	}

	public static void show(Context context, @StringRes int textRes) {
		if (toast == null) {
			toast = Toast.makeText(context,textRes,Toast.LENGTH_SHORT);
		}
		toast.setText(textRes);
		toast.show();
	}

	public static void showLong(Context context, CharSequence text) {
		if (longToast == null) {
			longToast = Toast.makeText(context,text,Toast.LENGTH_LONG);
		}
		longToast.setText(text);
		longToast.show();
	}

	public static void showLong(Context context, @StringRes int textRes) {
		if (longToast == null) {
			longToast = Toast.makeText(context,textRes,Toast.LENGTH_LONG);
		}
		longToast.setText(textRes);
		longToast.show();
	}////////////////

	private ToastUtils(Context context) {
		mToast = Toast.makeText(context.getApplicationContext(), null, Toast.LENGTH_SHORT);
	}

	public static synchronized ToastUtils getInstanc(Context context) {
		if (null == mToastUtils) {
			mToastUtils = new ToastUtils(context);
		}
		return mToastUtils;
	}
	/**
	 * 显示toast
	 *
	 * @param toastMsg
	 */
	public void showToast(int toastMsg) {
		mToast.setText(toastMsg);
		mToast.show();
	}

	/**
	 * 显示toast
	 *
	 * @param toastMsg
	 */
	public void showToast(String toastMsg) {
		mToast.setText(toastMsg);
		mToast.show();
	}

	/**
	 * 取消toast，在activity的destory方法中调用
	 */
	public void destory() {
		if (null != mToast) {
			mToast.cancel();
			mToast = null;
		}
		mToastUtils = null;
	}

}
