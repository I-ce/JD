package com.six.jd;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;


import com.six.jd.R;
import com.six.jd.base.BaseActivity;
import com.six.jd.ui.carts.fragment.CartsFragment;
import com.six.jd.ui.classify.fragment.ClassifyFragment;
import com.six.jd.ui.find.fragment.FindFragment;
import com.six.jd.ui.home.fragment.HomeFragment;
import com.six.jd.ui.mine.fragment.MineFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;
    private ArrayList<android.support.v4.app.Fragment> list;
    private Fragment currentfragment;
    private FragmentManager fragmentManager;

    @Override
    protected void initListener() {
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        rb4.setOnClickListener(this);
        rb5.setOnClickListener(this);
    }
    @Override
    protected void initData() {
        list = new ArrayList<>();

        list.add(new HomeFragment());
        list.add(new ClassifyFragment());
        list.add(new FindFragment());
        list.add(new CartsFragment());
        list.add(new MineFragment());

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame,new HomeFragment()).commit();
    }

    @Override
    public void onClick(View v) {
        //开启事务
        switch (v.getId()) {
            case R.id.rb1:
                changeFragment(new HomeFragment());
                break;
            case R.id.rb2:
                changeFragment(new ClassifyFragment());
                break;
            case R.id.rb3:
                changeFragment(new FindFragment());
                break;
            case R.id.rb4:
                changeFragment(new CartsFragment());
                break;
            case R.id.rb5:
                changeFragment(new MineFragment());
                break;
        }
    }

    private void changeFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(R.id.frame,fragment).commit();
    }
}
