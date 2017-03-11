package cn.ac.bigo.backend.model.po;

/**
 * Created by zwb on 2017/3/10.
 */
public class ApplyListByDatePoShow {
    private int id;
    private long bigoID;
    private String applyDate;//申请时间
    private String addTime;//添加申请时间
    private String remarks = "";//备注
    private float duration = 0;//时长
    private boolean isScheduled = false;
    private int oldScheduleId;//旧排班id

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

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public int getOldScheduleId() {
        return oldScheduleId;
    }

    public void setOldScheduleId(int oldScheduleId) {
        this.oldScheduleId = oldScheduleId;
    }
}
