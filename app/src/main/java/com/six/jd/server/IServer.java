package com.six.jd.server;

import com.six.jd.bean.ClassDingDanbean;
import com.six.jd.bean.ClassItemBean;
import com.six.jd.bean.ClassLeftBean;
import com.six.jd.bean.ClassRightBean;
import com.six.jd.bean.ClassXQBean;
import com.six.jd.bean.ClassdeleteCartBean;
import com.six.jd.bean.GetOrdersbean;
import com.six.jd.bean.HomeBannerBean;
import com.six.jd.bean.LoginBean;
import com.six.jd.bean.RegBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IServer {
    //登陆
    @POST("user/login")
    Observable<LoginBean> login(@Query("mobile") String mobile, @Query("password") String password);

    //注册
    @POST("user/reg")
    Observable<RegBean> regist(@Query("mobile") String mobile, @Query("password") String password);

    @POST("home/getHome")
    Observable<HomeBannerBean> homeBannerData();

    @POST("product/getCatagory")
    Observable<ClassLeftBean> ClassLeftBeanData();

    @POST("product/getProductCatagory")
    Observable<ClassRightBean> ClassRightBeanData(@Query("cid") String s);

    @POST("product/getProducts")
    Observable<ClassItemBean> ClassItemData(@Query("pscid") String s);

    @POST("product/searchProducts")
    Observable<ClassItemBean> ClassSearchData(@Query("keywords") String keywords, @Query("page") String page,@Query("sort")String sort);

    @POST("product/getProductDetail")
    Observable<ClassXQBean> ClassXQData(@Query("pid") String pid);

    @POST("product/addCart")
    Observable<ClassAddCartBean> ClassAddCartData(@Query("uid") String uid,@Query("pid")String pid);
    @POST("product/getCarts")
    Observable<ClassGetCartBean> ClassGetCartData(@Query("uid") String uid);

    @POST("product/updateCarts")
    Observable<ClassUpdateBean> ClassUpdateData(@Query("uid") String uid,@Query("sellerid")String sellerid,@Query("pid")String pid,@Query("selected")String selected,@Query("num")String num);
    @POST("product/deleteCart")
    Observable<ClassdeleteCartBean> ClassdeleteCartData(@Query("uid") String uid, @Query("pid")String pid);

    @POST("product/createOrder")
    Observable<ClassDingDanbean> ClassDingDandata(@Query("uid") String uid, @Query("price") String price);

    @POST("product/getOrders")
    Observable<GetOrdersbean> GetOrdersdata(@Query("uid") String uid);



}
