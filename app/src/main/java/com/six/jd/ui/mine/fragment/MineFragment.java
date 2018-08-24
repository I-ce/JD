package com.six.jd.ui.mine.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.six.jd.R;
import com.six.jd.base.BaseFragment;
import com.six.jd.bean.LoginMessageEvent;
import com.six.jd.ui.mine.activity.LoginActivity;
import com.six.jd.ui.mine.activity.OutLoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MineFragment extends BaseFragment implements View.OnClickListener {
    private ImageView user;
    private TextView dlzc;
    private ImageView sz;
    private ImageView xx;
    private RelativeLayout rela;
    private String username;
    private SharedPreferences sp;

    @Override
    protected void initListener() {
        user.setOnClickListener(this);
        dlzc.setOnClickListener(this);
        sz.setOnClickListener(this);
        xx.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        if (sp.getBoolean("auto_boolean",false)){
            dlzc.setText(sp.getString("username",""));
        }else{
            dlzc.setText("登陆/注册 >");
        }
    }

    @Override
    protected void initView(View view) {
        user = view.findViewById(R.id.user);
        dlzc = view.findViewById(R.id.dlzc);
        sz = view.findViewById(R.id.sz);
        xx = view.findViewById(R.id.xx);
        rela = view.findViewById(R.id.rela);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int privideLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user:
                if (sp.getBoolean("login",false)) {
                    Intent intent = new Intent(getActivity(),OutLoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.dlzc:
                //如果已经登陆跳转到设置界面
                if (sp.getBoolean("login",false)) {
                    Intent intent = new Intent(getActivity(),OutLoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.sz:
                break;
            case R.id.xx:
                break;
        }
    }
    //事件处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(LoginMessageEvent messageEvent){
        dlzc.setText(messageEvent.getMsg());
    }
    //解除注册
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}
