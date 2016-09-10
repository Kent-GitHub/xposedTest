package com.easynetwork.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.easynetwork.ad.manager.AdBannerManager;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Kent ↗↗↗ on 2016/9/5.
 */
public class ThirdActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        setContentView(tv);
        EventBus.getDefault().post("i got it");
        EventBus.getDefault().post(1);
        EventBus.getDefault().post(AdBannerManager.getInstance());
    }
}
