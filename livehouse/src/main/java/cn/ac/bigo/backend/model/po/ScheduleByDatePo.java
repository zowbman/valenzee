package cn.ac.bigo.backend.model.po;

import cn.ac.bigo.base.model.po.StartTimePo;

/**
 * Created by zwb on 2017/3/7.
 */
public class ScheduleByDatePo extends StartTimePo {
    private int scheduleId = -1;//默认-1 无
    private String bigoID = "未选择";
    private String whatsAppNumber = "未选择";
    private float duration = 0;//时长
    private boolean isScheduled = false;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getBigoID() {
        return bigoID;
    }

    public void setBigoID(String bigoID) {
        this.bigoID = bigoID;
    }

    public String getWhatsAppNumber() {
        return whatsAppNumber;
    }

    public void setWhatsAppNumber(String whatsAppNumber) {
        this.whatsAppNumber = whatsAppNumber;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setScheduled(boolean scheduled) {
        isScheduled = scheduled;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}
