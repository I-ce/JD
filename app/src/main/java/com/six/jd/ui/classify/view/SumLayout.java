package com.six.jd.ui.classify.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.six.jd.R;

public class SumLayout extends LinearLayout implements View.OnClickListener {

    private TextView sub;
    private TextView count;
    private TextView add;
    private OnDownSumLayouListener onDownSumLayoutListener;

    public SumLayout(Context context) {
        this(context,null);
    }

    public SumLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SumLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = View.inflate(context, R.layout.sumlayout, this);

        sub = view.findViewById(R.id.sub);
        count = view.findViewById(R.id.count);
        add = view.findViewById(R.id.add);
        //注册点击事件
        sub.setOnClickListener(this);
        add.setOnClickListener(this);
    }



    public String getCount() {
        return count.getText().toString();
    }

    public void setCount(String count) {
        this.count.setText(count);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sub:
                String ss = count.getText().toString();
                int i = Integer.parseInt(ss);
                i--;
                if (i > 0){
                    setCount(i+"");
                    onDownSumLayoutListener.onDownSumLayout();
                }else{

                }
                break;
            case R.id.add:
                String s = count.getText().toString();
                int i1 = Integer.parseInt(s);
                i1++;
                setCount(i1+"");
                onDownSumLayoutListener.onDownSumLayout();
                break;
        }
    }
    public interface OnDownSumLayouListener {
        void onDownSumLayout();
    }
    //外部访问接口的方法
    public void setOnDownSumLayoutListener(OnDownSumLayouListener onDownSumLayoutListener) {
        this.onDownSumLayoutListener = onDownSumLayoutListener;
    }

}
