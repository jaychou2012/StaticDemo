package me.zuichu.staticlib.manager;

/**
 * Created by office on 2018/5/10.
 * 事件统计实体
 */

public class StaticEvent {
    private String eventId;
    private String label;
    private long time;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
