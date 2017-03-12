package cn.ac.bigo.backend.model.po;

import cn.ac.bigo.base.util.ConcurrentDateTimeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by zwb on 2017/2/28.
 */
public class WhiteListPo {
    @JsonIgnore
    private int addTime;//添加时间
    private long bigoID;//bigoID
    private int fraction;//分数
    private String addTimeStr;

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTimeStr = ConcurrentDateTimeUtil.format(addTime * 1000L);
        this.addTime = addTime;
    }

    public long getBigoID() {
        return bigoID;
    }

    public void setBigoID(long bigoID) {
        this.bigoID = bigoID;
    }

    public int getFraction() {
        return fraction;
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }

    public String getAddTimeStr() {
        return addTimeStr;
    }

    public void setAddTimeStr(String addTimeStr) {
        this.addTimeStr = addTimeStr;
    }
}
