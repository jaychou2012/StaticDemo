package me.zuichu.staticdemo.base;

import android.app.Application;

import me.zuichu.staticlib.config.StaticConfig;
import me.zuichu.staticlib.manager.StaticLog;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StaticConfig.init(this);
        StaticLog.logOpen = false;
    }
}
