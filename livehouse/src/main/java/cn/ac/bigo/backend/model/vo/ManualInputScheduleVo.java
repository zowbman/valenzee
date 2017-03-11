package cn.ac.bigo.backend.model.vo;

/**
 * Created by zwb on 2017/3/12.
 */
public class ManualInputScheduleVo {
    private String date;
    private long bigoID;
    private String whatsAppNumber;
    private int timeSlot;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return "date:" + this.date + ",bigoID:" + this.bigoID + ",whatsAppNumber:" + whatsAppNumber + ",timeSlot:" + timeSlot;
    }
}
