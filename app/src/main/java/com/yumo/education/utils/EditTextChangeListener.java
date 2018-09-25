package com.yumo.education.utils;

/***
 * 方便写的一个工具类,因此必然要写接口(提高扩展性)
 */
public interface EditTextChangeListener {
    // 是否所有的EditText中都有内容
    void allHasContent(boolean isHasContent);
    //Buton 是否可点击
}
