package cn.ac.bigo.frontend.model.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * Created by zwb on 2017/2/28.
 */
public class ApplyVo {
    private long bigoID;//bigoId
    private String whatsAppNumber;//手机号码
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyDate;//申请日期
    private int startTime;//开始时间
    private int duration;//持续时间

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

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
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

    @Override
    public String toString() {
        return "bigoID:" + this.bigoID + ",whatsAppNumber:" + this.whatsAppNumber + ",applyDate:" + applyDate + ",startTime:" + startTime + ",duration:" + duration;
    }
}
