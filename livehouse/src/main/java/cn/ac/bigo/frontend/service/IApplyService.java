package cn.ac.bigo.frontend.service;

import cn.ac.bigo.backend.model.po.ApplyListByDatePo;
import cn.ac.bigo.backend.model.po.ApplyPo;
import cn.ac.bigo.frontend.model.vo.ApplyVo;

import java.util.Date;
import java.util.List;

/**
 * Created by zwb on 2017/2/27.
 */
public interface IApplyService {
    /**
     * 申请排班
     *
     * @param applyVo
     * @return
     */
    boolean apply(ApplyVo applyVo);

    /**
     * 根据日期获取申请排班列表
     * @param applyDate
     * @param startTime
     * @return
     */
    List<ApplyListByDatePo> getApplyListByDuration(Date applyDate, int startTime);

    /**
     * 根据日期获取申请排班列表
     *
     * @param applyDate
     * @param startTime
     * @return
     */
    List<ApplyListByDatePo> getApplyListByDurationAndIsNotMe(Date applyDate, int startTime, int scheduleId);

    /**
     * 根据id查询申请排班信息
     *
     * @param id
     * @return
     */
    ApplyPo getPeddingApplyById(int id);
}
