package com.yumo.education.model;

import java.io.File;


/**
 * Created by asus on 2016/9/9.
 */
public class User {
    private File headIcon;
    private String sex;
    private float balance;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public File getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(File headIcon) {
        this.headIcon = headIcon;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public static User getCurrentUser(Class<User> userClass) {
        return null;
    }
}
