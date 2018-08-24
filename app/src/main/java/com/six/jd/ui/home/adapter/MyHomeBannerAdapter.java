package com.six.jd.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.six.jd.R;
import com.six.jd.bean.HomeBannerBean;
import com.six.jd.bean.ProductListBean;
import com.six.jd.ui.classify.activity.ClassAddCartActivity;
import com.six.jd.ui.home.holder.HomeBannerHolder;
import com.six.jd.ui.home.holder.HomeMiaoshaHolder;
import com.six.jd.ui.home.holder.HomeTuijianHolder;
import com.six.jd.ui.home.holder.HomeViewPagerHolder;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class MyHomeBannerAdapter extends RecyclerView.Adapter {

    private Context context;
    private HomeBannerBean.DataBean list;
    private ArrayList<String> icons;

    private ViewGroup points;//小圆点指示器
    private ImageView[] ivPoints;//小圆点图片集合
    private int totalPage;//总的页数
    private int mPageSize = 10;//每页显示的最大数量
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private int currentPage;//当前页
    private int ONE_LAYOUT = 0;
    private int TWO_LALYOUT = 1;
    private View view;
    private int THREE_LAYOUT = 2;
    private int FOUR_LAYOUT = 3;

    public MyHomeBannerAdapter(Context context, HomeBannerBean.DataBean list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return ONE_LAYOUT;
        }else if(position == 1){
            return TWO_LALYOUT;
        }else if((position+2)%2==0){
            return THREE_LAYOUT;
        }else if((position+2)%2==1){
            return FOUR_LAYOUT;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ONE_LAYOUT) {
            view = View.inflate(context, R.layout.home_banner_layout, null);
            return new HomeBannerHolder(view);
        }else if(viewType == TWO_LALYOUT){
            view = View.inflate(context, R.layout.home_viewpager, null);
            return new HomeViewPagerHolder(view);
        }else if(viewType == THREE_LAYOUT){
            view = View.inflate(context,R.layout.home_miaosha,null);
            return new HomeMiaoshaHolder(view);
        }else if(viewType == FOUR_LAYOUT){
            view = View.inflate(context,R.layout.home_tuijian,null);
            return new HomeTuijianHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeBannerHolder) {
            icons = new ArrayList<>();
            for (int i = 0; i < list.getBanner().size(); i++) {
                icons.add(list.getBanner().get(i).getIcon());
            }
            ((HomeBannerHolder) holder).home_xbanner.setData(icons, null);
            ((HomeBannerHolder) holder).home_xbanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(context).load(icons.get(position)).into((ImageView) view);
                }
            });
            ((HomeBannerHolder) holder).home_xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                @Override
                public void onItemClick(XBanner banner, Object model, int position) {
                    Intent intent = new Intent(context, ClassAddCartActivity.class);
                    context.startActivity(intent);
                }
            });
        } else if (holder instanceof HomeViewPagerHolder) {
            LayoutInflater inflater = LayoutInflater.from(context);

            //总的页数，取整（这里有三种类型：Math.ceil(3.5)=4:向上取整，只要有小数都+1  Math.floor(3.5)=3：向下取整  Math.round(3.5)=4:四舍五入）
            totalPage = (int) Math.ceil(list.getFenlei().size() * 1.0 / mPageSize);
            viewPagerList = new ArrayList<>();
            for (int i = 0; i < totalPage; i++) {
                //每个页面都是inflate出一个新实例
                GridView gridView = (GridView) inflater.inflate(R.layout.home_viewpager_grid, ((HomeViewPagerHolder) holder).home_viewpager, false);
                gridView.setAdapter(new MyGridViewAdapter(context, list.getFenlei(), i, mPageSize));
                viewPagerList.add(gridView);
            }
            ((HomeViewPagerHolder) holder).home_viewpager.setAdapter(new MyViewPagerAdapter(viewPagerList));
            //小圆点指示器
            ivPoints = new ImageView[totalPage];
            for (int i = 0; i < ivPoints.length; i++) {
                ImageView imageView = new ImageView(context);
                //设置图片的宽高
                imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
//                if(i == 0){
//                    imageView.setBackgroundResource(R.drawable.page__selected_indicator);
//                }else{
//                    imageView.setBackgroundResource(R.drawable.page__normal_indicator);
//                }
                ivPoints[i] = imageView;
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                layoutParams.leftMargin = 20;//设置点点点view的左边距
                layoutParams.rightMargin = 20;//设置点点点view的右边距
                ((HomeViewPagerHolder) holder).home_viewpager_points.addView(imageView, layoutParams);
            }
            //设置ViewPager滑动监听
            ((HomeViewPagerHolder) holder).home_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
//                    setImageBackground(i);
                    currentPage = i;
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
        }else if (holder instanceof HomeMiaoshaHolder){
            ((HomeMiaoshaHolder) holder).home_miaosha.setLayoutManager(new GridLayoutManager(context,2));
            ((HomeMiaoshaHolder) holder).home_miaosha_title.setText(list.getMiaosha().getName());
            ((HomeMiaoshaHolder) holder).home_miaosha.setAdapter(new MyMiaoshaAdapter(context,list.getMiaosha()));
        }else if(holder instanceof  HomeTuijianHolder){
            ((HomeTuijianHolder) holder).home_tuijian_recyview.setLayoutManager(new GridLayoutManager(context,2));
            ((HomeTuijianHolder) holder).home_tuijian_title.setText(list.getTuijian().getName());
            ((HomeTuijianHolder) holder).home_tuijian_recyview.setAdapter(new MyTuijianAdapter(context,list.getTuijian()));
        }


    }

//    private void setImageBackground(int selectItems) {
//        for (int i = 0; i < ivPoints.length; i++) {
//            if (i == selectItems) {
//                ivPoints[i].setBackgroundResource(R.drawable.page__selected_indicator);
//            } else {
//                ivPoints[i].setBackgroundResource(R.drawable.page__normal_indicator);
//            }
//        }
//    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
