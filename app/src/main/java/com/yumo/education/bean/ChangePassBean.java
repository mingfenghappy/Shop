package com.yumo.education.bean;

public class ChangePassBean {
    /**
     * resultCode : 0
     * msg : 密码修改成功
     * data : {}
     */

    private int resultCode;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
    }

//    /**
//     * resultCode : 0
//     * msg : string
//     * data : {"userId":394}
//     */
//
//    private int resultCode;
//    private String msg;
//    private RegisterBean.DataBean data;
//
//    public int getResultCode() {
//        return resultCode;
//    }
//
//    public void setResultCode(int resultCode) {
//        this.resultCode = resultCode;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public RegisterBean.DataBean getData() {
//        return data;
//    }
//
//    public void setData(RegisterBean.DataBean data) {
//        this.data = data;
//    }

//    public static class DataBean {
//        /**
//         * userId : 394
//         */
//
//        private int userId;
//
//        public int getUserId() {
//            return userId;
//        }
//
//        public void setUserId(int userId) {
//            this.userId = userId;
//        }
//    }
}
