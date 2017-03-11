package cn.ac.bigo.backend.controller;

import cn.ac.bigo.backend.model.po.ApplyListByDatePo;
import cn.ac.bigo.backend.model.po.ApplyListByDatePoShow;
import cn.ac.bigo.backend.model.po.ApplyPo;
import cn.ac.bigo.backend.model.po.SaveSchedulePo;
import cn.ac.bigo.backend.model.po.ScheduleByDatePo;
import cn.ac.bigo.backend.model.po.SchedulePo;
import cn.ac.bigo.backend.model.vo.ManualInputScheduleVo;
import cn.ac.bigo.base.cache.StartTimeCache;
import cn.ac.bigo.base.controller.BaseController;
import cn.ac.bigo.base.helper.CodeHelper;
import cn.ac.bigo.base.model.po.PubReturnMsg;
import cn.ac.bigo.base.model.po.StartTimePo;
import cn.ac.bigo.base.util.ConcurrentDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zwb on 2017/3/6.排班控制器
 */
@Controller
@RequestMapping("/backend/json")
public class ScheduleController extends BaseController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    StartTimeCache startTimeCache;

    /**
     * 获取排班信息
     *
     * @return
     */
    @GetMapping("/v1/getScheduleInfo")
    @ResponseBody
    public PubReturnMsg getScheduleInfo(Date date) {
        log.info("getScheduleInfo param date:[{}]", date);
        if (date == null) {
            date = new Date();//当前日期
        }
        List<StartTimePo> startTimePos = iStartTimeService.findAll();//查询所有开始时间
        List<SchedulePo> schedulePos = iScheduleService.getScheduleInfoByDate(date);//根据日期查询排班信息
        List<ScheduleByDatePo> scheduleByDatePos = new ArrayList<>();
        startTimePos.forEach((startTimePo) -> {
            final ScheduleByDatePo scheduleByDatePo = new ScheduleByDatePo();
            scheduleByDatePo.setId(startTimePo.getId());
            StringBuilder sb = new StringBuilder();
            sb.append(startTimePo.getStartTime());
            sb.append(" ~ ");
            sb.append(startTimeCache.getStartTime(startTimePo.getId() + 1));
            scheduleByDatePo.setStartTime(sb.toString());
            schedulePos.stream().filter((schedulePo) -> schedulePo.getTimeSlot().indexOf(String.valueOf(startTimePo.getId())) != -1).forEach((s) ->
                    {
                        int duration = s.getTimeSlot().split(",").length;
                        scheduleByDatePo.setDuration(duration / 2f);
                        scheduleByDatePo.setScheduleId(s.getId());
                        scheduleByDatePo.setBigoID(String.valueOf(s.getBigoID()));
                        scheduleByDatePo.setWhatsAppNumber(s.getWhatsAppNumber());
                        scheduleByDatePo.setScheduled(true);
                    }
            );

            scheduleByDatePos.add(scheduleByDatePo);
        });
        return new PubReturnMsg(CodeHelper.SUCCESS, scheduleByDatePos);
    }

    /**
     * 获取申请排班列表
     *
     * @param applyDate
     * @param startTimeId
     * @param
     * @return
     */
    @GetMapping("/v1/getApplyListForAdd")
    @ResponseBody
    public PubReturnMsg getApplyListForAdd(Date applyDate, int startTimeId) {
        log.info("getApplyListForAdd param applyDate:[{}],startTimeId;[{}]", applyDate, startTimeId);
        if (applyDate == null || startTimeId <= 0) {
            return new PubReturnMsg(CodeHelper.PARAMS_ERROR, new ArrayList<>());
        }
        List<String> scheduledTimeSlot = iScheduleService.getTimeSlotListByDate(applyDate);
        List<ApplyListByDatePo> applyListByDatePos = iApplyService.getApplyListByDuration(applyDate, startTimeId);
        List<ApplyListByDatePoShow> applyListByDatePoShows = new ArrayList<>();
        applyListByDatePos.forEach((applyListByDatePo) -> {
            final ApplyListByDatePoShow applyListByDatePoShow = new ApplyListByDatePoShow();
            applyListByDatePoShow.setId(applyListByDatePo.getId());
            applyListByDatePoShow.setBigoID(applyListByDatePo.getBigoID());
            applyListByDatePoShow.setAddTime(ConcurrentDateUtil.format(applyListByDatePo.getAddTime() * 1000L));//申请添加时间
            String startTime = startTimeCache.getStartTime(applyListByDatePo.getStartTime());//开始时间
            String endTime = startTimeCache.getStartTime(applyListByDatePo.getStartTime() + applyListByDatePo.getDuration());//结束时间
            StringBuilder sb = new StringBuilder();
            sb.append(applyListByDatePo.getApplyDate());
            sb.append("(");
            sb.append(startTime);
            sb.append(" ~ ");
            sb.append(endTime);
            sb.append(")");
            applyListByDatePoShow.setApplyDate(sb.toString());//申请时间
            applyListByDatePoShow.setDuration(applyListByDatePo.getDuration() / 2f);//时长
            //备注信息处理，如timeSlot时间段的endTime已经排班则提示
            String timeSlot = String.valueOf(applyListByDatePo.getDuration() == 1 ? startTimeId : startTimeId + 1);
            scheduledTimeSlot.stream().filter((s) -> s.indexOf(timeSlot) != -1 || s.indexOf(String.valueOf(startTimeId)) != -1).forEach((s) -> {
                applyListByDatePoShow.setRemarks("申请时间段已被排班");
                applyListByDatePoShow.setScheduled(true);
            });
            applyListByDatePoShows.add(applyListByDatePoShow);
        });
        return new PubReturnMsg(CodeHelper.SUCCESS, applyListByDatePoShows);
    }

    /**
     * 获取申请排班列表
     *
     * @param applyDate
     * @param startTimeId
     * @param scheduleId
     * @return
     */
    @GetMapping("/v1/getApplyListForEdit")
    @ResponseBody
    public PubReturnMsg getApplyListForEdit(Date applyDate, int startTimeId, int scheduleId) {
        log.info("getApplyListForEdit param applyDate:[{}],startTimeId;[{}],scheduleId[{}]", applyDate, startTimeId, scheduleId);
        if (applyDate == null || startTimeId <= 0 || scheduleId == -1) {
            return new PubReturnMsg(CodeHelper.PARAMS_ERROR, new ArrayList<>());
        }
        List<String> scheduledTimeSlot = iScheduleService.getTimeSlotListByDateAndIsNotMe(applyDate, scheduleId);
        List<ApplyListByDatePo> applyListByDatePos = iApplyService.getApplyListByDurationAndIsNotMe(applyDate, startTimeId, scheduleId);
        List<ApplyListByDatePoShow> applyListByDatePoShows = new ArrayList<>();
        applyListByDatePos.forEach((applyListByDatePo) -> {
            final ApplyListByDatePoShow applyListByDatePoShow = new ApplyListByDatePoShow();
            applyListByDatePoShow.setId(applyListByDatePo.getId());
            applyListByDatePoShow.setBigoID(applyListByDatePo.getBigoID());
            applyListByDatePoShow.setAddTime(ConcurrentDateUtil.format(applyListByDatePo.getAddTime() * 1000L));//申请添加时间
            String startTime = startTimeCache.getStartTime(applyListByDatePo.getStartTime());//开始时间
            String endTime = startTimeCache.getStartTime(applyListByDatePo.getStartTime() + applyListByDatePo.getDuration());//结束时间
            StringBuilder sb = new StringBuilder();
            sb.append(applyListByDatePo.getApplyDate());
            sb.append("(");
            sb.append(startTime);
            sb.append(" ~ ");
            sb.append(endTime);
            sb.append(")");
            applyListByDatePoShow.setApplyDate(sb.toString());//申请时间
            applyListByDatePoShow.setDuration(applyListByDatePo.getDuration() / 2f);//时长
            applyListByDatePoShow.setOldScheduleId(scheduleId);//旧排班id
            //备注信息处理，如timeSlot时间段的endTime已经排班则提示
            String timeSlot = String.valueOf(applyListByDatePo.getDuration() == 1 ? startTimeId : startTimeId + 1);
            scheduledTimeSlot.stream().filter((s) -> s.indexOf(timeSlot) != -1 || s.indexOf(String.valueOf(startTimeId)) != -1).forEach((s) -> {
                applyListByDatePoShow.setRemarks("申请时间段已被排班");
                applyListByDatePoShow.setScheduled(true);
            });
            applyListByDatePoShows.add(applyListByDatePoShow);
        });
        return new PubReturnMsg(CodeHelper.SUCCESS, applyListByDatePoShows);
    }

    /**
     * 排班申请通过
     *
     * @param applyId
     * @return
     */
    @PostMapping("/v1/passApplyForAdd")
    @ResponseBody
    public PubReturnMsg passApplyForAdd(int applyId) {
        log.info("passApply param applyId:[{}]", applyId);
        if (applyId <= 0) {
            return new PubReturnMsg(CodeHelper.PARAMS_ERROR);
        }
        ApplyPo applyPo = iApplyService.getPeddingApplyById(applyId);
        if (applyPo == null) {
            log.error("passApplyForAdd 参数错误,申请信息为空 applyPo is null");
            return new PubReturnMsg(CodeHelper.PARAMS_ERROR);
        }
        try {
            List<String> scheduledTimeSlot = iScheduleService.getTimeSlotListByDate(ConcurrentDateUtil.parse(applyPo.getApplyDate()));
            boolean isPedding = true;
            String startTime = String.valueOf(applyPo.getStartTime());
            String endTime = String.valueOf(applyPo.getDuration() == 1 ? applyPo.getStartTime() : applyPo.getStartTime() + 1);
            log.info("passApplyForAdd startTime;[{}],endTime;[{}]", startTime, endTime);
            if (scheduledTimeSlot != null && !scheduledTimeSlot.isEmpty()) {
                for (String s : scheduledTimeSlot) {
                    if (s.indexOf(startTime) > -1 || s.indexOf(endTime) > -1) {
                        isPedding = false;
                        break;
                    }
                }
            }
            if (!isPedding) {
                log.error("passApplyForAdd 参数错误,申请时段已经安排班次");
                return new PubReturnMsg(CodeHelper.PARAMS_ERROR);
            }
            SaveSchedulePo saveSchedulePo = new SaveSchedulePo();
            saveSchedulePo.setBigoID(applyPo.getBigoID());
            saveSchedulePo.setWhatsAppNumber(applyPo.getWhatsAppNumber());//电话号码
            StringBuilder sb = new StringBuilder();
            sb.append(applyPo.getStartTime());
            if (applyPo.getDuration() == 2) {
                sb.append(",");
                sb.append(applyPo.getStartTime() + 1);
            }
            saveSchedulePo.setTimeSlot(sb.toString());//时间段
            saveSchedulePo.setDate(applyPo.getApplyDate());//申请日期
            saveSchedulePo.setApplyId(applyPo.getId());
            boolean result = iScheduleService.passAplly(saveSchedulePo);
            if (result) {
                return new PubReturnMsg(CodeHelper.SUCCESS);
            }
        } catch (ParseException e) {
            log.error("catch ", e);
        }
        return new PubReturnMsg(CodeHelper.FAILURE);
    }

    /**
     * 排班修改（替换方式）
     *
     * @param oldScheduleId
     * @param applyId
     * @return
     */
    @PostMapping("/v1/passApplyForEdit")
    @ResponseBody
    public PubReturnMsg passApplyForEdit(int oldScheduleId, int applyId) {
        log.info("passApplyForEdit param oldScheduleId:[{}],applyId;[{}]", oldScheduleId, applyId);
        if (oldScheduleId <= 0 || applyId <= 0) {
            return new PubReturnMsg(CodeHelper.PARAMS_ERROR);
        }
        //移除现有排班
        boolean result = iScheduleService.removeSchedule(oldScheduleId);
        if (!result) {
            log.error("iScheduleService.removeSchedule移除排班失败 result返回值为false");
            return new PubReturnMsg(CodeHelper.FAILURE);
        }
        //添加新排班
        ApplyPo applyPo = iApplyService.getPeddingApplyById(applyId);
        if (applyPo == null) {
            log.error("passApplyForEdit 参数错误,申请信息为空 applyPo is null");
            return new PubReturnMsg(CodeHelper.PARAMS_ERROR);
        }
        try {
            List<String> scheduledTimeSlot = iScheduleService.getTimeSlotListByDate(ConcurrentDateUtil.parse(applyPo.getApplyDate()));
            boolean isPedding = true;
            String startTime = String.valueOf(applyPo.getStartTime());
            String endTime = String.valueOf(applyPo.getDuration() == 1 ? applyPo.getStartTime() : applyPo.getStartTime() + 1);
            log.info("passApplyForEdit startTime;[{}],endTime;[{}]", startTime, endTime);
            if (scheduledTimeSlot != null && !scheduledTimeSlot.isEmpty()) {
                for (String s : scheduledTimeSlot) {
                    if (s.indexOf(startTime) > -1 || s.indexOf(endTime) > -1) {
                        isPedding = false;
                        break;
                    }
                }
            }
            if (!isPedding) {
                log.error("passApplyForEdit 参数错误,申请时段已经安排班次");
                return new PubReturnMsg(CodeHelper.PARAMS_ERROR);
            }
            SaveSchedulePo saveSchedulePo = new SaveSchedulePo();
            saveSchedulePo.setBigoID(applyPo.getBigoID());
            saveSchedulePo.setWhatsAppNumber(applyPo.getWhatsAppNumber());//电话号码
            StringBuilder sb = new StringBuilder();
            sb.append(applyPo.getStartTime());
            if (applyPo.getDuration() == 2) {
                sb.append(",");
                sb.append(applyPo.getStartTime() + 1);
            }
            saveSchedulePo.setTimeSlot(sb.toString());//时间段
            saveSchedulePo.setDate(applyPo.getApplyDate());//申请日期
            saveSchedulePo.setApplyId(applyPo.getId());
            result = iScheduleService.passAplly(saveSchedulePo);
            if (result) {
                return new PubReturnMsg(CodeHelper.SUCCESS);
            }
        } catch (ParseException e) {
            log.error("catch ", e);
        }
        return new PubReturnMsg(CodeHelper.FAILURE);
    }

    /**
     * 移除排班
     *
     * @param id
     * @return
     */
    @PostMapping("/v1/removeSchedule")
    @ResponseBody
    public PubReturnMsg removeSchedule(int id) {
        log.info("removeSchedule param id:[{}]", id);
        if (id <= 0) {
            return new PubReturnMsg(CodeHelper.PARAMS_ERROR);
        }
        boolean result = iScheduleService.removeSchedule(id);
        if (result) {
            return new PubReturnMsg(CodeHelper.SUCCESS);
        }
        return new PubReturnMsg(CodeHelper.FAILURE);
    }

    /**
     * 手动输入排班信息
     *
     * @param manualInputScheduleVo
     * @param oldScheduleId
     * @return
     */
    @PostMapping("/v1/manualInputSchedule")
    @ResponseBody
    public PubReturnMsg manualInputSchedule(ManualInputScheduleVo manualInputScheduleVo, int oldScheduleId) {
        log.info("manualInputSchedule param manualInputScheduleVo:[{}],oldScheduleId:[{}]", manualInputScheduleVo.toString(), oldScheduleId);
        boolean result = true;
        if (oldScheduleId != -1) {
            result = iScheduleService.removeSchedule(oldScheduleId);
        }
        if (result) {
            try {
                List<String> scheduledTimeSlot = iScheduleService.getTimeSlotListByDate(ConcurrentDateUtil.parse(manualInputScheduleVo.getDate()));
                boolean isPedding = true;
                if (scheduledTimeSlot != null && !scheduledTimeSlot.isEmpty()) {
                    for (String s : scheduledTimeSlot) {
                        if (s.indexOf(manualInputScheduleVo.getTimeSlot()) > -1) {
                            isPedding = false;
                            break;
                        }
                    }
                }
                if (!isPedding) {
                    log.error("passApplyForAdd 参数错误,申请时段已经安排班次");
                    return new PubReturnMsg(CodeHelper.PARAMS_ERROR);
                }

                SaveSchedulePo saveSchedulePo = new SaveSchedulePo();
                saveSchedulePo.setDate(manualInputScheduleVo.getDate());//排班日期
                saveSchedulePo.setApplyId(0);
                saveSchedulePo.setTimeSlot(String.valueOf(manualInputScheduleVo.getTimeSlot()));
                saveSchedulePo.setWhatsAppNumber(manualInputScheduleVo.getWhatsAppNumber());
                saveSchedulePo.setBigoID(manualInputScheduleVo.getBigoID());
                result = iScheduleService.passAplly(saveSchedulePo);
                if (result) {
                    return new PubReturnMsg(CodeHelper.SUCCESS);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new PubReturnMsg(CodeHelper.FAILURE);
    }
}
