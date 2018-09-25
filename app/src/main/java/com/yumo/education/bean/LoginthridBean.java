package com.yumo.education.bean;

public class LoginthridBean {
    /**
     * resultCode : 0
     * msg : 登录成功
     * data : {"userId":394}
     */

    private int resultCode;
    private String msg;
    private LoginthridBean.DataBean data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LoginthridBean.DataBean getData() {
        return data;
    }

    public void setData(LoginthridBean.DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * openId : 394
         */

        private int openId;

        public int getOpenId() {
            return openId;
        }

        public void setUserId(int userId) {
            this.openId = openId;
        }
    }
}

