package cn.ac.bigo.backend.model.po;

/**
 * Created by zwb on 2017/3/11.
 */
public class ApplyPo {
    private int id;
    private long bigoID;
    private String whatsAppNumber;
    private String applyDate;
    private int startTime;
    private int duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getBigoID() {
        return bigoID;
    }

    public void setBigoID(long bigoID) {
        this.bigoID = bigoID;
    }

    public String getWhatsAppNumber() {
        return whatsAppNumber;
    }

    public void setWhatsAppNumber(String whatsAppNumber) {
        this.whatsAppNumber = whatsAppNumber;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
