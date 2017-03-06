package cn.ac.bigo.backend.model.po;

/**
 * Created by zwb on 2017/2/28.
 */
public class WhiteListPo {
    private int addTime;//添加时间
    private long bigoID;//bigoID
    private int fraction;//分数

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
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
}
