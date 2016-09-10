package com.easynetwork.test;

import android.app.Activity;
import android.app.Dialog;
import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.easynetwork.ad.bean.AdPlatform;
import com.easynetwork.ad.bean.AdListener;
import com.easynetwork.ad.manager.AdBannerManager;
import com.easynetwork.ad.manager.AdInstlManager;
import com.easynetwork.ad.manager.AdManager;
import com.easynetwork.ad.manager.AdSplashManager;
import com.easynetwork.ad.utils.ScreenUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {

    TextView tv;
    ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_test).setOnClickListener(this);
        findViewById(R.id.test_content).setOnClickListener(this);
        tv = new TextView(this);
        viewGroup = (ViewGroup) findViewById(R.id.test_layout);
        EventBus.getDefault().register(this);
        try {
            Class clazz = Class.forName("android.hardware.SystemSensorManager$SensorEventQueue");
            Log.e(TAG, "onCreate: clazz: " + clazz.getName());
            Field mSensorsEvents = clazz.getDeclaredField("mSensorsEvents");
            Log.e(TAG, "onCreate: mSensorsEvents: " + mSensorsEvents.getName());
            SparseArray<SensorEvent> sensors;
            mSensorsEvents.setAccessible(true);
            SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            List<Sensor> list = sm.getSensorList(Sensor.TYPE_STEP_COUNTER);
            sm.registerListener(sel, list.get(0), SensorManager.SENSOR_DELAY_UI);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                startActivity(new Intent(this, SecActivity.class));
                break;
            case R.id.test_content:
//                logContent();
                break;
        }
    }

    private SensorEventListener sel = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            String str = "";
            for (float f : event.values) {
                str += f + "_";
            }

            Log.e(TAG, "onSensorChanged: " + str + "_" + event.timestamp);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            Log.e(TAG, "onAccuracyChanged: " + accuracy);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.unregisterListener(sel);
    }

    @Subscribe
    public void onEvent(int o) {
        Button btn = (Button) findViewById(R.id.btn_test);
        btn.setText(o);
    }

//    @Subscribe
//    public void onEvent(int ab) {
//        Button btn = (Button) findViewById(R.id.btn_test);
//        btn.setText(btn.getText() + ab.getClass().getName());
//    }

    private static final String TAG = "MainActivity";

//    private void logContent() {
//        Uri uri = Uri.parse("content://com.easynetwork.weatherData/WeatherCache");
//        ContentResolver resolver = getContentResolver();
//        Cursor cursor = resolver.query(uri, null, null, null, null);
//
//        while (cursor != null && cursor.moveToNext()) {
//            //获得城市
//            String city = cursor.getString(cursor.getColumnIndex("city"));
//            //获得日期
//            String date = cursor.getString(cursor.getColumnIndex("date"));
//            //获得温度
//            String rtTmp = cursor.getString(cursor.getColumnIndex("rtTmp"));
//            //获得天气描述
//            String describe = cursor.getString(cursor.getColumnIndex("describe"));
//            //获得天气代码
//            String code = cursor.getString(cursor.getColumnIndex("code"));
//            Log.e(TAG, "logContent: " + city + "_" + date + "_" + rtTmp + "_" + describe + "_" + code);
//        }
//        if (cursor != null) {
//            cursor.close();
//        }
//    }
}
