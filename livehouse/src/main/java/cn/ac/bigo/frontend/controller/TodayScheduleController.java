package cn.ac.bigo.frontend.controller;

import cn.ac.bigo.backend.model.po.SchedulePo;
import cn.ac.bigo.base.cache.StartTimeCache;
import cn.ac.bigo.base.controller.BaseController;
import cn.ac.bigo.base.helper.CodeHelper;
import cn.ac.bigo.base.model.po.PubReturnMsg;
import cn.ac.bigo.base.model.po.StartTimePo;
import cn.ac.bigo.frontend.model.po.TodaySchedulePo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zwb on 2017/3/12.
 */
@Controller
@RequestMapping("/frontend/json")
public class TodayScheduleController extends BaseController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    StartTimeCache startTimeCache;

    /**
     * @return
     */
    @GetMapping("/v1/todaySchedule")
    @ResponseBody
    private PubReturnMsg todaySchedule() {
        List<StartTimePo> startTimePos = iStartTimeService.findAll();//查询所有开始时间
        List<SchedulePo> schedulePos = iScheduleService.getScheduleInfoByDate(new Date());//根据日期查询排班信息
        List<TodaySchedulePo> todaySchedulePos = new ArrayList<>();
        startTimePos.forEach((startTimePo) -> {
            final TodaySchedulePo todaySchedulePo = new TodaySchedulePo();
            StringBuilder sb = new StringBuilder();
            sb.append(startTimePo.getStartTime());
            sb.append(" ~ ");
            sb.append(startTimeCache.getStartTime(startTimePo.getId() + 1));
            todaySchedulePo.setStartTime(sb.toString());
            schedulePos.stream().filter((schedulePo) -> schedulePo.getTimeSlot().indexOf(String.valueOf(startTimePo.getId())) != -1).forEach((s) ->
                    {
                        int duration = s.getTimeSlot().split(",").length;
                        todaySchedulePo.setDuration(duration / 2f);
                        todaySchedulePo.setBigoID(String.valueOf(s.getBigoID()));
                        todaySchedulePo.setScheduled(true);
                    }
            );

            todaySchedulePos.add(todaySchedulePo);
        });
        return new PubReturnMsg(CodeHelper.SUCCESS, todaySchedulePos);
    }
}
