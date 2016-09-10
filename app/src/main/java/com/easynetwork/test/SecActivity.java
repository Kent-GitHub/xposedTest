package com.easynetwork.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Kent ↗↗↗ on 2016/9/5.
 */
public class SecActivity extends Activity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sec_activity);
        EventBus.getDefault().register(this);
        tv = (TextView) findViewById(R.id.tv2);
        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecActivity.this, ThirdActivity.class);
                startActivity(i);
            }
        });
    }

//    @Subscribe
//    public void onEvent(String o) {
//        tv.setText(o);
//    }

    @Subscribe
    public void onEvent(int o) {
        tv.setText(tv.getText() + "" + o);
    }

}
