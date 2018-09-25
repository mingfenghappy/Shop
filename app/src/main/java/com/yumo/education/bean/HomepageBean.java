package com.yumo.education.bean;

import java.util.List;

public class HomepageBean {

    /**
     * resultCode : 0
     * msg : 获取首页成功！
     * data : {"slideShowUrlList":[{"id":1,
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
        private List<SlideShowUrlListBean> slideShowUrlList;
        private List<ClassifyListBean> classifyList;
        private List<RecommendationListBean> recommendationList;

        public List<SlideShowUrlListBean> getSlideShowUrlList() {
            return slideShowUrlList;
        }

        public void setSlideShowUrlList(List<SlideShowUrlListBean> slideShowUrlList) {
            this.slideShowUrlList = slideShowUrlList;
        }

        public List<ClassifyListBean> getClassifyList() {
            return classifyList;
        }

        public void setClassifyList(List<ClassifyListBean> classifyList) {
            this.classifyList = classifyList;
        }

        public List<RecommendationListBean> getRecommendationList() {
            return recommendationList;
        }

        public void setRecommendationList(List<RecommendationListBean> recommendationList) {
            this.recommendationList = recommendationList;
        }

        public static class SlideShowUrlListBean {
            /**
             * id : 1
             * pictureurl : http://192.168.0.103:8080/Test/images/slid1.png
             * shopid : 2
             */

            private int id;
            private String pictureurl;
            private int shopid;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPictureurl() {
                return pictureurl;
            }

            public void setPictureurl(String pictureurl) {
                this.pictureurl = pictureurl;
            }

            public int getShopid() {
                return shopid;
            }

            public void setShopid(int shopid) {
                this.shopid = shopid;
            }
        }

        public static class ClassifyListBean {
            /**
             * id : 1
             * classifypictureurl : http://192.168.0.103:8080/Test/images/yiyao.png
             * classifyid : 0
             * classifytitle : 医药保健
             */

            private int id;
            private String classifypictureurl;
            private int classifyid;
            private String classifytitle;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getClassifypictureurl() {
                return classifypictureurl;
            }

            public void setClassifypictureurl(String classifypictureurl) {
                this.classifypictureurl = classifypictureurl;
            }

            public int getClassifyid() {
                return classifyid;
            }

            public void setClassifyid(int classifyid) {
                this.classifyid = classifyid;
            }

            public String getClassifytitle() {
                return classifytitle;
            }

            public void setClassifytitle(String classifytitle) {
                this.classifytitle = classifytitle;
            }
        }

        public static class RecommendationListBean {
            /**
             * shopid : 1
             * shoptitle : 优卡丹复方小儿退热栓
             * shopprice : 16
             * shopfreight : 5
             * shopsalesvolume : 100
             * shoptype : 0
             * shoppictureurl : http://192.168.0.103:8080/Test/images/aaa.jpg
             * shopgraphicdetails : https://in.m.jd.com/product/jieshao/2402692.html
             * classifyid : 0
             * twoclassifyid : 1
             * recommend : 1
             * shopCount : -18
             */

            private int shopid;
            private String shoptitle;
            private int shopprice;
            private int shopfreight;
            private int shopsalesvolume;
            private int shoptype;
            private String shoppictureurl;
            private String shopgraphicdetails;
            private int classifyid;
            private int twoclassifyid;
            private int recommend;
            private int shopCount;

            public int getShopid() {
                return shopid;
            }

            public void setShopid(int shopid) {
                this.shopid = shopid;
            }

            public String getShoptitle() {
                return shoptitle;
            }

            public void setShoptitle(String shoptitle) {
                this.shoptitle = shoptitle;
            }

            public int getShopprice() {
                return shopprice;
            }

            public void setShopprice(int shopprice) {
                this.shopprice = shopprice;
            }

            public int getShopfreight() {
                return shopfreight;
            }

            public void setShopfreight(int shopfreight) {
                this.shopfreight = shopfreight;
            }

            public int getShopsalesvolume() {
                return shopsalesvolume;
            }

            public void setShopsalesvolume(int shopsalesvolume) {
                this.shopsalesvolume = shopsalesvolume;
            }

            public int getShoptype() {
                return shoptype;
            }

            public void setShoptype(int shoptype) {
                this.shoptype = shoptype;
            }

            public String getShoppictureurl() {
                return shoppictureurl;
            }

            public void setShoppictureurl(String shoppictureurl) {
                this.shoppictureurl = shoppictureurl;
            }

            public String getShopgraphicdetails() {
                return shopgraphicdetails;
            }

            public void setShopgraphicdetails(String shopgraphicdetails) {
                this.shopgraphicdetails = shopgraphicdetails;
            }

            public int getClassifyid() {
                return classifyid;
            }

            public void setClassifyid(int classifyid) {
                this.classifyid = classifyid;
            }

            public int getTwoclassifyid() {
                return twoclassifyid;
            }

            public void setTwoclassifyid(int twoclassifyid) {
                this.twoclassifyid = twoclassifyid;
            }

            public int getRecommend() {
                return recommend;
            }

            public void setRecommend(int recommend) {
                this.recommend = recommend;
            }

            public int getShopCount() {
                return shopCount;
            }

            public void setShopCount(int shopCount) {
                this.shopCount = shopCount;
            }
        }
    }
}
