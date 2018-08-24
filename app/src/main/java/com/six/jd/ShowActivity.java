package com.six.jd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
/* *
 * @author 王前峰
 * @time 2018/8/10:16:32:
 * @purpose 初进页面
 * */
public class ShowActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private int time=1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                if (time>0){
                    time--;
                    handler.sendEmptyMessageDelayed(1,1000);
                }else{
                    Intent intent=new Intent(ShowActivity.this, MainActivity.class);
                    startActivity(intent);
                    handler.removeCallbacksAndMessages(null);
                    finish();
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //初始化sharedprererence轻量级存储
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        //刚进来的时候给轻量级存储初赋值
        boolean flag = sp.getBoolean("flag", true);
        //判断值
        if (flag){
            handler.sendEmptyMessageDelayed(1,1000);
            //之后赋为false 不再进来
            //sp.edit().putBoolean("flag",false).commit();
        }else{
            Intent intent=new Intent(ShowActivity.this, MainActivity.class);
            startActivity(intent);
            handler.removeCallbacksAndMessages(null);
            finish();
        }
    }
}
