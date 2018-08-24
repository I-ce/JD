package com.six.jd.ui.mine.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.six.jd.R;
import com.six.jd.base.BaseActivity;
import com.six.jd.bean.LoginMessageEvent;

import org.greenrobot.eventbus.EventBus;

public class OutLoginActivity extends BaseActivity implements View.OnClickListener {


    private Button out_login_btn;
    private SharedPreferences sp;

    @Override
    protected void initListener() {
        out_login_btn.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_out_login);
        out_login_btn = findViewById(R.id.out_login_btn);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.out_login_btn:
                sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.clear();
                edit.commit();
                EventBus.getDefault().post(new LoginMessageEvent("登陆/注册 >"));
                finish();
                break;
        }
    }
}
