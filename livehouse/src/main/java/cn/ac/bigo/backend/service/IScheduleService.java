package cn.ac.bigo.backend.service;

import cn.ac.bigo.backend.model.po.SaveSchedulePo;
import cn.ac.bigo.backend.model.po.SchedulePo;
import cn.ac.bigo.backend.model.vo.ManualInputScheduleVo;

import java.util.Date;
import java.util.List;

/**
 * Created by zwb on 2017/3/6.
 */
public interface IScheduleService {
    /**
     * 根据日期获取排班信息
     *
     * @param date
     * @return
     */
    List<SchedulePo> getScheduleInfoByDate(Date date);

    /**
     * 根据日期获取已排班时间段
     *
     * @param date
     * @return
     */
    List<String> getTimeSlotListByDate(Date date);

    /**
     * 根据日期获取已排班时间段(不包含自己)
     *
     * @param id
     * @return
     */
    List<String> getTimeSlotListByDateAndIsNotMe(Date date, int id);

    /**
     * 申请通过
     *
     * @param saveSchedulePo
     * @return
     */
    boolean passAplly(SaveSchedulePo saveSchedulePo);

    /**
     * 移除指定排班
     *
     * @param id
     * @return
     */
    boolean removeSchedule(int id);
}
