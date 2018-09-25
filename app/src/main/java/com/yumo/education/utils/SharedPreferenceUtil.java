package com.yumo.education.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者 蔡佳彬
 * 时间 2017/7/5
 * 类说明{}
 */
public class SharedPreferenceUtil {

    /**
     * SharePreference存储路径
     */

    public static final String PREFERENCE_NAME = "Education";
    public static SharedPreferences preference;
    public static void init(Context context , String name){
        preference = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }
    public static void SaveData(String name, Object data){
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(name, String.valueOf(data));
        editor.commit();
    }
    public static boolean getBooleanData(String name){
       return Boolean.valueOf(preference.getString(name,"false"));
    }
    public static String getStringData(String name){
        return preference.getString(name,"");
    }
    public static int getIntData(String name){
        return Integer.valueOf(preference.getString(name,"0"));
    }
    public static double getDoubleData(String name){
        return Double.valueOf(preference.getString(name,"0"));
    }
    public static long getLongData(String name){
        return Long.valueOf(preference.getString(name,"0"));
    }
    /**
     * put boolean to SharedPreferences
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key,value);
        return editor.commit();
    }

    /**
     * get boolean from SharedPreferences
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        return preferences.getBoolean(key,defaultValue);
    }

    /**
     * get boolean from SharedPreferences
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context,key,false);
    }

}
