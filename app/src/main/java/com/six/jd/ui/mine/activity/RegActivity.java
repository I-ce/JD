package com.six.jd.ui.mine.activity;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.six.jd.R;
import com.six.jd.api.Api;
import com.six.jd.base.BaseActivity;
import com.six.jd.bean.RegBean;
import com.six.jd.bean.RegMessageEvent;
import com.six.jd.ui.mine.presenter.APresenter;
import com.six.jd.ui.mine.view.IregistView;

import org.greenrobot.eventbus.EventBus;

public class RegActivity extends BaseActivity implements IregistView, View.OnClickListener {
    private int i=1;
    private String name;
    private APresenter presenter;
    private String name1;
    private String pwd1;
    private ImageView back1;
    private EditText userName1;
    private EditText userPwd1;
    private ImageView xy1;
    private Button zc1;
    @Override
    protected void initListener() {
        zc1.setOnClickListener(this);
        xy1.setOnClickListener(this);
        back1.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        name = getIntent().getStringExtra("name");
        userName1.setText(name);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_reg);
        back1=findViewById(R.id.back1);
        userName1=findViewById(R.id.user_name1);
        userPwd1=findViewById(R.id.user_pwd1);
        xy1=findViewById(R.id.xy1);
        zc1=findViewById(R.id.zc1);
        presenter = new APresenter(this);
        presenter.getRegistDatas(Api.LRBASE,name1,pwd1);
    }

    @Override
    public void callbackZc(RegBean regBean) {
        Toast.makeText(RegActivity.this,regBean.getMsg(),Toast.LENGTH_SHORT).show();
        if (regBean.getMsg().contains("注册成功")) {
            Toast.makeText(RegActivity.this, regBean.getMsg(), Toast.LENGTH_SHORT).show();
            finish();
        }
        if (regBean.getMsg().contains("天呢！用户已注册")){
            Toast.makeText(RegActivity.this,regBean.getMsg()+"请直接登陆",Toast.LENGTH_SHORT).show();
            /*Intent intent=new Intent(RegistActivity.this,LoginActivity.class);
            startActivity(intent);*/
            EventBus.getDefault().post(new RegMessageEvent(name1));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back1:
                finish();
                break;
            case R.id.xy1:
                i++;
                if (i%2==0){
                    Toast toast = Toast.makeText(RegActivity.this, "显示", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    userPwd1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    Toast toast = Toast.makeText(RegActivity.this, "隐藏", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    userPwd1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.zc1:
                name1 = userName1.getText().toString();
                pwd1 = userPwd1.getText().toString();
                if (!TextUtils.isEmpty(name1)&&!TextUtils.isEmpty(pwd1)){
                    presenter.getRegistDatas(Api.LRBASE,name1,pwd1);
                }else{
                    Toast.makeText(RegActivity.this,"请输入完整内容",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
