package cn.ac.bigo.backend.model.po;

/**
 * Created by zwb on 2017/3/11.
 */
public class SaveSchedulePo {
    private long bigoID;
    private String whatsAppNumber;
    private String timeSlot;
    private String date;
    private int applyId;

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

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }
}
