package cn.ac.bigo.backend.service.impl;

import cn.ac.bigo.backend.mapper.ScheduleMapper;
import cn.ac.bigo.backend.model.po.SaveSchedulePo;
import cn.ac.bigo.backend.model.po.SchedulePo;
import cn.ac.bigo.backend.service.IScheduleService;
import cn.ac.bigo.base.util.ConcurrentDateUtil;
import cn.ac.bigo.frontend.mapper.ApplyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zwb on 2017/3/6.
 */
@Service
public class ScheduleServiceImpl implements IScheduleService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ScheduleMapper scheduleMapper;

    @Autowired
    ApplyMapper applyMapper;

    @Override
    public List<SchedulePo> getScheduleInfoByDate(Date date) {
        if (date == null) {
            date = new Date();//当前日期
        }
        return scheduleMapper.getScheduleInfoByDate(ConcurrentDateUtil.format(date));
    }

    @Override
    public List<String> getTimeSlotListByDate(Date date) {
        if (date == null) {
            return new ArrayList<>();
        } else {
            return scheduleMapper.getTimeSlotListByDate(date);
        }
    }

    @Override
    public List<String> getTimeSlotListByDateAndIsNotMe(Date date, int id) {
        if (date == null || id == -1) {
            return new ArrayList<>();
        }
        return scheduleMapper.getTimeSlotListByDateAndIsNotMe(date, id);
    }

    @Override
    public boolean passAplly(SaveSchedulePo saveSchedulePo) {
        boolean isResult = true;
        if (saveSchedulePo.getApplyId() > 0) {
            isResult = applyMapper.updateIsPassById(saveSchedulePo.getApplyId(), 2);//(2:成功)
        }
        if (!isResult) {
            log.error("passAplly 更新排班申请信息状态 error,param applyId:[{}]", saveSchedulePo.getApplyId());
            return isResult;
        }
        return scheduleMapper.saveScheduleInfo(saveSchedulePo.getBigoID(), saveSchedulePo.getWhatsAppNumber(), saveSchedulePo.getTimeSlot(), saveSchedulePo.getDate(), saveSchedulePo.getApplyId());
    }

    @Override
    public boolean removeSchedule(int id) {
        boolean result = false;
        if (id == -1) {
            log.error("removeSchedule 移除排班参数错误 param error,id:[{}]", id);
            return result;
        }
        int applyId = scheduleMapper.getApplyIdById(id);
        result = scheduleMapper.delScheduleById(id);//删除排班
        if (result && applyId > 0) {
            result = applyMapper.updateIsPassById(applyId, 0);//(0:待定)
        }
        return result;
    }
}