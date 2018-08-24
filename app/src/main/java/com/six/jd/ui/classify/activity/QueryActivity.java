package com.six.jd.ui.classify.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.six.jd.R;
import com.six.jd.base.BaseActivity;
import com.six.jd.bean.ClassItemBean;
import com.six.jd.bean.ClassLeftBean;
import com.six.jd.bean.ClassRightBean;
import com.six.jd.bean.ClassXQBean;
import com.six.jd.server.ClassGetCartBean;
import com.six.jd.ui.classify.adapter.MyClassItemAdapter;
import com.six.jd.ui.classify.prensenter.ClassPresenter;
import com.six.jd.ui.classify.view.ClassView;

import java.util.List;

public class QueryActivity extends BaseActivity implements ClassView, RadioGroup.OnCheckedChangeListener {

    private XRecyclerView class_xq_recycleview;
    private int page = 1;
    private ClassPresenter presenter;
    private MyClassItemAdapter adapter;
    private EditText home__et_message;
    private ImageView home__iv_sou;
    private String edit_name;
    private RadioGroup class_xq_radio_Group;
    private int sort = 0;
    @Override
    protected void initListener() {
        class_xq_radio_Group.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        home__et_message.setText(intent.getStringExtra("edit_name"));
        presenter = new ClassPresenter(this);
        class_xq_recycleview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        class_xq_recycleview.setPullRefreshEnabled(true);
        class_xq_recycleview.setLoadingMoreEnabled(true);
        edit_name = home__et_message.getText().toString();
        presenter.getLiebIaoData(edit_name, page,sort);
        home__iv_sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QueryActivity.this, "Aaaaaaa", Toast.LENGTH_SHORT).show();
                edit_name = home__et_message.getText().toString();
                presenter.getLiebIaoData(edit_name, page, sort);
            }
        });
        class_xq_recycleview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.getLiebIaoData(edit_name, page, sort);
                class_xq_recycleview.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getLiebIaoData(edit_name, page, sort);
                class_xq_recycleview.refreshComplete();
            }
        });
    }
    @Override
    protected void initView() {
        setContentView(R.layout.activity_query);
        class_xq_recycleview = findViewById(R.id.class_xq_recycleview);
        home__et_message = findViewById(R.id.home__et_message);
        home__iv_sou = findViewById(R.id.home__iv_sou);
        class_xq_radio_Group = findViewById(R.id.class_xq_radio_Group);
    }

    @Override
    public void getSuccess(List<ClassLeftBean.DataBean> data) {
    }

    @Override
    public void getError() {

    }

    @Override
    public void getSuccess(List<ClassRightBean.DataBean> data, String s) {

    }

    @Override
    public void getSuccessQuerry(List<ClassItemBean.DataBean> dataBeans) {
        adapter = new MyClassItemAdapter(QueryActivity.this, dataBeans);
        class_xq_recycleview.setAdapter(adapter);
        adapter.setCallClassItemBack(new MyClassItemAdapter.CallClassItemBack() {
            @Override
            public void getUrl(String pid) {
                Intent intent = new Intent(QueryActivity.this, XQActivity.class);
                intent.putExtra("pid", pid);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getSuccessXq(ClassXQBean classXQBean) {

    }

    @Override
    public void getSuccessCart(ClassGetCartBean classGetCartBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.class_xq_btn_zonghe:
                sort = 0;
                presenter.getLiebIaoData(edit_name,page,sort);
                break;
            case R.id.class_xq_btn_xiaoliang:
                sort = 1;
                presenter.getLiebIaoData(edit_name,page,sort);
                break;
            case R.id.class_xq_btn_jiage:
                sort = 2;
                presenter.getLiebIaoData(edit_name,page,sort);
                break;

        }
    }
}
