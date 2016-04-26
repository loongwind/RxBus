package com.cm.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.cm.rxbus.RxBus;
import com.cm.rxbus.Subscribe;
import com.cm.rxbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        RxBus.getDefault().register(this);
    }


    @Subscribe
    public void onEvent(String s) {
        Log.e("CMAD", "------>" + s);
    }

    @Subscribe
    public void onEvent(EventA eventA) {
        Log.e("CMAD", "---onEvent EventA-->" + eventA.text);
    }

    @Subscribe(code = 102)
    public void onEventWithCode(EventA eventA) {
        Log.e("CMAD", "---onEvent EventA 102-->" + eventA.text);
    }

    @Subscribe(code = 103, threadMode = ThreadMode.MAIN)
    public void onEventWithCodeAndThreadMode(EventA eventA) {
        Log.e("CMAD", "---onEvent EventB 103--->" + eventA.text);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unRegister(this);
    }


    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                RxBus.getDefault().post("123456");
                break;
            case R.id.btn2:
                RxBus.getDefault().post(new EventA());
                break;
            case R.id.btn3:
                RxBus.getDefault().post(102, new EventA("event code 102"));
                break;
            case R.id.btn4:
                RxBus.getDefault().post(103, new EventA("event code 103"));
                break;
        }
    }


    class EventA {
        public String text = "BeanA";

        public EventA() {

        }

        public EventA(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "EventA{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }


}

