package com.johnzero.notification.util;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * @author: JohnZero
 * @date: 2020-12-05
 **/
public class Utils {
    private static final String TAG = "江湖";

    public static void log(String str) {
        Log.d(TAG, str);
    }

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void toastSync(Context context, String msg) {
        Looper.prepare();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

}
