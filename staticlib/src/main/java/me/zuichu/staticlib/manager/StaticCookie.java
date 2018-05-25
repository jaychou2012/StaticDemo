package me.zuichu.staticlib.manager;

import java.io.Serializable;
import java.util.List;

/**
 * Created by office on 2018/4/13.
 * 定义唯一用户属性标识
 */

public class StaticCookie implements Serializable {
    private String uid;
    private long startTime;
    private long endTime;
    private long duration;
    private long number;
    private String netType;
    private String model;
    private String pixel;
    private String versionName;
    private int versionCode;
    private String packageName;
    private String androidVersion;
    private List<StaticSession> sessionList;

    public List<StaticSession> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<StaticSession> sessionList) {
        this.sessionList = sessionList;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getPixel() {
        return pixel;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    @Override
    public String toString() {
        String string = "Uid-" + getUid() + ",StartTime-" + getStartTime() + ",EndTime-" + getEndTime()
                + ",Duration-" + getDuration() + ",Number-" + getNumber() + ",NetType-" + getNetType() + ",Model-" + getModel() + ",AndroidVersion-" + getAndroidVersion()
                + ",PackageName-" + getPackageName() + ",VersionName-" + getVersionName()
                + ",VersionCode-" + getVersionCode() + ",Pixel-" + getPixel();
        return string;
    }
}
