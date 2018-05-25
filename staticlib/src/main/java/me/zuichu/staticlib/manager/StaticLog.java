package me.zuichu.staticlib.manager;

import android.util.Log;

/**
 * Created by office on 2018/4/16.
 * 日志统计
 */


public class StaticLog {
    public static boolean logOpen = false;
    private static String TAG = "info";

    public static void LogI(String text) {
        if (logOpen) {
            Log.i(TAG, text);
        }

    }

    public static void LogD(String text) {
        if (logOpen) {
            Log.d(TAG, text);
        }
    }

    public static void LogE(String text) {
        if (logOpen) {
            Log.e(TAG, text);
        }
    }

    public static void LogV(String text) {
        if (logOpen) {
            Log.i(TAG, text);
        }
    }

    public static void LogW(String text) {
        if (logOpen) {
            Log.w(TAG, text);
        }
    }
}
