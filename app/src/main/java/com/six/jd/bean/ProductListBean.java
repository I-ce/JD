package com.six.jd.bean;

import java.io.Serializable;

public class ProductListBean implements Serializable{
    private String proName;
    private String imgUrl;

    public ProductListBean(String proName, String imgUrl) {
        this.proName = proName;
        this.imgUrl = imgUrl;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
