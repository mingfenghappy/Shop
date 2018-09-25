package com.yumo.education.bean;

/**
 * Created by Administrator on 2017/3/10 0010.
 */

public class MainBottomBean extends CheckBean {
    private int imgID;
    private int checkimgID;
    private String name;

    public MainBottomBean(int imgID, int checkimgID, String name) {
        this.imgID = imgID;
        this.checkimgID = checkimgID;
        this.name = name;
    }

    public int getCheckimgID() {
        return checkimgID;
    }

    public void setCheckimgID(int checkimgID) {
        this.checkimgID = checkimgID;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
