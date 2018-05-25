package me.zuichu.staticlib.manager;

import java.io.Serializable;

/**
 * Created by office on 2018/4/13.
 * 定义用户行为的一次会话过程
 */

public class StaticSession implements Serializable {
    private long startTime;
    private long endTime;
    private long duration;
    private String path;
    private int depth;
    private String tagName;
    private String activityName;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "staticSession" + startTime + ":" + "startTime-" + startTime + "," + "endTime-" + endTime + "," + "duration-" + duration + "," + "path-" + path + "," + "depth-" + depth;
    }
}
