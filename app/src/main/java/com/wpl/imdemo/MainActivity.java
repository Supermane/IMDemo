package com.wpl.imdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;
    private RadioGroup rg;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private RadioButton btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String Token = "TWBTo6DDWNhuvLE9oe6Upnbekd2/SmgwA98t4BBJ95i4aNBWrplEV+ezozs/Lzm32mKvoqlyTWP8wzLuusv6Jg==";

        //获取控件
        initview();

        //设置fragment
        setFragment();

        //默认
        btn1.setChecked(true);
        switchFragment("fragment1");

        RongIM.connect(Token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                //Connect Token 失效的状态处理，需要重新获取 Token
            }

            @Override
            public void onSuccess(String userId) {
                Log.e("MainActivity", "——onSuccess— -" + userId);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("MainActivity", "——onError— -" + errorCode);
            }
        });

    }

    /**
     * 获取控件
     */
    private void initview() {
        FrameLayout fragment = (FrameLayout) findViewById(R.id.fragment);
        rg = (RadioGroup) findViewById(R.id.rg);
        btn1 = (RadioButton) findViewById(R.id.btn1);
    }

    /**
     * 进行操作fragment
     */
    private void setFragment() {
        //创建一个管理类
        manager = getSupportFragmentManager();
        //创建一个事务
        FragmentTransaction transaction = manager.beginTransaction();
        //实例化fragment类
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        //将fragment添加进事务
        transaction.add(R.id.fragment, fragment1, "fragment1");
        transaction.add(R.id.fragment, fragment2, "fragment2");
        //提交
        transaction.commit();

        //rg的监听事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn1:
                        switchFragment("fragment1");
                        break;
                    case R.id.btn2:
                        switchFragment("fragment2");
                        break;
                }
            }
        });
    }

    /**
     * 选择fragment
     */
    private void switchFragment(String tag) {
        // 事物
        FragmentTransaction transaction = manager.beginTransaction();
        if ("fragment1".equals(tag)) {
            transaction.show(fragment1);
            transaction.hide(fragment2);
        } else if ("fragment2".equals(tag)) {
            transaction.show(fragment2);
            transaction.hide(fragment1);
        }
        transaction.commit();
    }
}
