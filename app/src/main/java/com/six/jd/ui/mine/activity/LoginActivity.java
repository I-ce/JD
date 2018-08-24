package com.six.jd.ui.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.six.jd.R;
import com.six.jd.api.Api;
import com.six.jd.base.BaseActivity;
import com.six.jd.bean.LoginBean;
import com.six.jd.bean.LoginMessageEvent;
import com.six.jd.bean.RegMessageEvent;
import com.six.jd.ui.mine.presenter.APresenter;
import com.six.jd.ui.mine.view.IloginView;
import com.six.jd.utils.RegularUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* *
 * @author 王前峰
 * @time 2018/8/10:16:23:
 * @purpose 登陆界面
 * */
public class LoginActivity extends BaseActivity implements View.OnClickListener,IloginView {

    private ImageView back;
    private TextView kf;
    private EditText userName;
    private EditText userPwd;
    private AutoCompleteTextView autoCompleteTextView;
    private ImageView xy;
    private Button dl;
    private Button zc;
    private String name;
    private String pwd;
    private int i=1;
    private Boolean flag = true;
    private APresenter presenter;
    private SharedPreferences sp;

    @Override
    protected void initListener() {
        back.setOnClickListener(this);
        xy.setOnClickListener(this);
        dl.setOnClickListener(this);
        zc.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        back = findViewById(R.id.back);
        kf=findViewById(R.id.kf);
        userName=findViewById(R.id.user_name);
        userPwd=findViewById(R.id.user_pwd);
        xy=findViewById(R.id.xy);
        dl=findViewById(R.id.dl);
        zc=findViewById(R.id.zc);
        EventBus.getDefault().register(this);
        presenter = new APresenter(this);
    }
    //绑定 回传值
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(RegMessageEvent messageEvent){
        userName.setText(messageEvent.getMsg());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.xy:
                if (flag){

                    userPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = false;
                }else{
                    userPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = true;
                }
                break;
            case R.id.dl:
                //获取用户名和密码 判断是否为空 进行登陆
                name = userName.getText().toString();
                pwd = userPwd.getText().toString();
                if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(pwd)){
                    if (RegularUtil.isMobile(name)){
                        presenter.getLoginDatas(Api.LRBASE,name,pwd);
                    }else{
                        Toast.makeText(LoginActivity.this,"必须输入手机号",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this,"请输入完整内容",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.zc:
                //获取用户名和密码 判断是否为空 进行注册
                Intent intent=new Intent(LoginActivity.this,RegActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void callback(LoginBean loginBean) {
        Toast.makeText(LoginActivity.this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();
        String msg = loginBean.getMsg();
        if (msg.contains("天呢！用户不存在")){
            Toast.makeText(LoginActivity.this,loginBean.getMsg()+"请注册！",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(LoginActivity.this,RegActivity.class);
            intent.putExtra("name",name);
            sp.edit().putBoolean("auto_boolean",false);
            startActivity(intent);
        }
        userPwd.setText("");
        if (msg.contains("登录成功")) {
            sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("username",loginBean.getData().getUsername());
            edit.putString("password",loginBean.getData().getPassword());
            edit.putString("uid",loginBean.getData().getUid());
            edit.putBoolean("auto_boolean",true);
            edit.putBoolean("login",true);
            edit.commit();
            EventBus.getDefault().post(new LoginMessageEvent(loginBean.getData().getUsername()));
            finish();
        }
    }
}
