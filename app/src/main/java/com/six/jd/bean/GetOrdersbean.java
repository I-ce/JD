package com.six.jd.bean;

import java.util.List;

public class GetOrdersbean {

    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2018-08-22T12:00:32","orderid":12364,"price":99.99,"status":0,"title":"订单测试标题16929","uid":16929},{"createtime":"2018-08-22T12:07:33","orderid":12366,"price":99.99,"status":0,"title":"订单测试标题16929","uid":16929}]
     * page : 1
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2018-08-22T12:00:32
         * orderid : 12364
         * price : 99.99
         * status : 0
         * title : 订单测试标题16929
         * uid : 16929
         */

        private String createtime;
        private String orderid;
        private double price;
        private String status;
        private String title;
        private String uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
