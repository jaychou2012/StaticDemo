package me.zuichu.staticlib.core;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.zuichu.staticlib.config.StaticConfig;
import me.zuichu.staticlib.manager.StaticEvent;
import me.zuichu.staticlib.manager.StaticSession;
import me.zuichu.staticlib.utils.Utils;

/**
 * Created by office on 2018/4/16.
 */

public class StaticApplication {
    public static List<StaticSession> sessions = new ArrayList<>();
    public static List<StaticSession> staticSessions = new ArrayList<>();
    public static List<StaticEvent> events = new ArrayList<>();
    private static int number = 0;

    public static void registerActivityListener(Application application) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    Utils.hasFilePemission(activity);
                    number = number + 1;
                    StaticSession staticSession = new StaticSession();
                    staticSession.setActivityName(activity.getClass().getSimpleName());
                    staticSession.setStartTime(System.currentTimeMillis());
                    staticSession.setTagName(activity.getLocalClassName());
                    addSeesion(staticSession);
                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {
                    StaticCore.resume(activity);
                }

                @Override
                public void onActivityPaused(Activity activity) {
                    StaticCore.pause(activity);
                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    finishSession(activity.getLocalClassName());
                    if (sessions.size() == 0) {
                        long closeTime = System.currentTimeMillis();
                        StaticConfig.getStaticCookie().setEndTime(closeTime);
                        StaticConfig.getStaticCookie().setDuration(closeTime - StaticConfig.getStaticCookie().getStartTime());
                        StaticConfig.getStaticCookie().setNumber(number);
                        Utils.saveStaticLogs(staticSessions);
                        number = 0;
                    }
                }
            });
        }
    }

    public static void addSeesion(StaticSession session) {
        if (sessions == null) {
            sessions = new ArrayList<>();
        }
        sessions.add(session);
    }

    public static void finishSession(String sessionKey) {
        if (sessions == null && sessions.isEmpty()) {
            return;
        }
        int position = Utils.contains(sessions, sessionKey);
        if (position != -1) {
            StaticSession staticSession = sessions.get(position);
            long endTime = System.currentTimeMillis();
            staticSession.setEndTime(endTime);
            staticSession.setDuration(endTime - staticSession.getStartTime());
            staticSession.setDepth(sessions.size());
            staticSession.setPath(getStackTrace());
            staticSessions.add(staticSession);
            sessions.remove(position);
        }
    }

    public static void finishAllSesion() {
        if (sessions == null && sessions.isEmpty()) {
            return;
        }
        for (StaticSession staticSession : sessions) {
            try {
                Class activityClass = Class.forName(staticSession.getActivityName());
                Activity activity = (Activity) activityClass.cast(Activity.class);
                activity.finish();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public List<StaticSession> getSessions() {
        return sessions;
    }

    public List<StaticEvent> getEvents() {
        return events;
    }

    public static String getStackTrace() {
        String path = "";
        for (StaticSession staticSession : sessions) {
            path = path + "->" + staticSession.getTagName();
        }
        return path;
    }

}
