package com.six.jd.ui.classify.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.six.jd.R;
import com.six.jd.base.BaseFragment;
import com.six.jd.bean.ClassItemBean;
import com.six.jd.bean.ClassLeftBean;
import com.six.jd.bean.ClassRightBean;
import com.six.jd.bean.ClassXQBean;
import com.six.jd.server.ClassGetCartBean;
import com.six.jd.ui.classify.adapter.MyClassLeftRecycleAdapter;
import com.six.jd.ui.classify.adapter.MyClassRightRecycleAdapter;
import com.six.jd.ui.classify.prensenter.ClassPresenter;
import com.six.jd.ui.classify.view.ClassView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends BaseFragment implements ClassView {


    private RecyclerView classify_left_recyview;
    private RecyclerView classify_right_recycle;
    private ClassPresenter presenter;
    private MyClassLeftRecycleAdapter left_adapter;

    public ClassifyFragment() {
        // Required empty public constructor
    }
    @Override
    protected void initListener() {

    }
    @Override
    protected void initData() {
        presenter = new ClassPresenter(this);
        presenter.getDatas();
        classify_left_recyview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        classify_right_recycle.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        presenter.getRightDatas("1");
    }

    @Override
    protected void initView(View view) {
            classify_left_recyview = view.findViewById(R.id.classify_left_recycle);
            classify_right_recycle = view.findViewById(R.id.classify_right_recycle);
    }
    @Override
    protected int privideLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    public void getSuccess(List<ClassLeftBean.DataBean> data) {
        left_adapter = new MyClassLeftRecycleAdapter(getActivity(), data);
        classify_left_recyview.setAdapter(left_adapter);
        left_adapter.setCidCallBack(new MyClassLeftRecycleAdapter.CidCallBack() {
            @Override
            public void getID(String id) {
                presenter.getRightDatas(id);
            }
        });
    }

    @Override
    public void getError() {

    }

    @Override
    public void getSuccess(List<ClassRightBean.DataBean> data, String s) {
        classify_right_recycle.setAdapter(new MyClassRightRecycleAdapter(getActivity(),data));

    }

    @Override
    public void getSuccessQuerry(List<ClassItemBean.DataBean> dataBeans) {

    }

    @Override
    public void getSuccessXq(ClassXQBean classXQBean) {

    }

    @Override
    public void getSuccessCart(ClassGetCartBean classGetCartBean) {

    }
}
