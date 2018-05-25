package me.zuichu.staticlib.config;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;

import me.zuichu.staticlib.core.StaticApplication;
import me.zuichu.staticlib.manager.StaticCookie;
import me.zuichu.staticlib.manager.StaticCrash;
import me.zuichu.staticlib.utils.Utils;

/**
 * Created by office on 2018/4/13.
 * 统计策略全局配置
 */

public class StaticConfig {
    public static StaticCookie staticCookie;

    public static String LOG_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "StaticLog.txt";

    public static String STATIC_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "Static.txt";

    public static final String RECORD_FLAG_CLOSEAPP = "staticCookies";//开启和退出App的时长

    public static enum NetType {
        WIFI, MOBILE_NET, NO_NET
    }

    private static NetType netType;

    public static void setUploadStrategy(NetType net) {
        netType = net;
    }

    public static NetType getUploadStrategy() {
        return netType;
    }

    public static void init(Context context) {
        staticCookie = new StaticCookie();
        staticCookie.setStartTime(System.currentTimeMillis());
        staticCookie.setUid(Utils.getImei(context) + "_" + Utils.getAdresseMAC(context));
        staticCookie.setNetType(Utils.getNetType(context));
        staticCookie.setPackageName(context.getPackageName());
        staticCookie.setVersionName(Utils.getVersionName(context));
        staticCookie.setVersionCode(Utils.getVersionCode(context));
        staticCookie.setAndroidVersion(android.os.Build.VERSION.RELEASE);
        staticCookie.setModel(android.os.Build.MODEL);
        staticCookie.setPixel(Utils.getScreenWidth(context) + "x" + Utils.getScreenHeight(context));
        StaticCrash.getInstance().init(context);
        StaticApplication.registerActivityListener((Application) context);
    }

    public static StaticCookie getStaticCookie() {
        return staticCookie;
    }
}
