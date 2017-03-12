package cn.ac.bigo.frontend.model.po;

/**
 * Created by zwb on 2017/3/12.
 */
public class TodaySchedulePo {
    private String startTime;
    private String bigoID = "未选择";
    private String whatsAppNumber = "未选择";
    private float duration = 0;//时长
    private boolean isScheduled = false;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setScheduled(boolean scheduled) {
        isScheduled = scheduled;
    }
}
