package me.zuichu.staticlib.core;

import android.app.Activity;

import me.zuichu.staticlib.manager.StaticEvent;

/**
 * Created by office on 2018/4/16.
 * 数据统计核心类
 */

public class StaticCore {

    /**
     * 统计页面浏览时长：开始
     *
     * @param activity
     */
    public static void resume(Activity activity) {

    }

    /**
     * 统计页面浏览时长：结束
     *
     * @param activity
     */
    public static void pause(Activity activity) {

    }

    /**
     * 统计页面浏览路径：结束
     *
     * @param activity
     */
    public static void onDestroy(Activity activity) {

    }

    /**
     * 事件点击统计
     *
     * @param eventId
     * @param label
     */
    public static void onEvent(String eventId, String label) {
        StaticEvent staticEvent = new StaticEvent();
        staticEvent.setEventId(eventId);
        staticEvent.setLabel(label);
        staticEvent.setTime(System.currentTimeMillis());
        StaticApplication.events.add(staticEvent);
    }
}
