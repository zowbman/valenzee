package cn.ac.bigo.base.model.po;

import java.sql.Time;

/**
 * Created by zwb on 2017/2/27.开始时间po类
 */
public class StartTimePo {
    private int id;
    private Time startTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
}
