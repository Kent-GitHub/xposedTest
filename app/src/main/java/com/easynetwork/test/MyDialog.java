package com.easynetwork.test;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Kent ↗↗↗ on 2016/9/5.
 */
public class MyDialog extends Dialog{
    public MyDialog(Context context) {
        super(context);
    }

    private static final String TAG = "MyDialog";

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: "+event.getDownTime() );
        Log.e(TAG, "onTouchEvent: "+event.getEventTime());
        Log.e(TAG, "onTouchEvent: "+event.getX());
        Log.e(TAG, "onTouchEvent: "+event.getY() );
        Log.e(TAG, "onTouchEvent: "+event.getMetaState() );
        return super.onTouchEvent(event);
    }
}
