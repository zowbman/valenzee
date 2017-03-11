package cn.ac.bigo.backend.model.po;

/**
 * Created by zwb on 2017/3/6.
 */
public class SchedulePo {
    private int id;
    private long bigoID;
    private String whatsAppNumber;
    private String timeSlot;

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

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}
