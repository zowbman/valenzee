package cn.ac.bigo.frontend.service.impl;

import cn.ac.bigo.backend.model.po.ApplyListByDatePo;
import cn.ac.bigo.backend.model.po.ApplyPo;
import cn.ac.bigo.base.util.BaseUtil;
import cn.ac.bigo.frontend.mapper.ApplyMapper;
import cn.ac.bigo.frontend.model.po.ApplyQueryPo;
import cn.ac.bigo.frontend.model.vo.ApplyVo;
import cn.ac.bigo.frontend.service.IApplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zwb on 2017/2/27.
 */
@Service
public class ApplyServiceImpl implements IApplyService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ApplyMapper applyMapper;

    @Override
    public boolean apply(ApplyVo applyVo) {
        return applyMapper.add(applyVo.getBigoID(), applyVo.getWhatsAppNumber(), applyVo.getApplyDate(), applyVo.getStartTime(), applyVo.getDuration(), BaseUtil.currentTimeMillis());
    }

    @Override
    public List<ApplyListByDatePo> getApplyListByDuration(Date applyDate, int startTime) {
        if (applyDate == null || startTime <= 0) {
            return new ArrayList<>();
        }
        return applyMapper.getApplyListByDuration(applyDate, startTime);
    }

    @Override
    public List<ApplyListByDatePo> getApplyListByDurationAndIsNotMe(Date applyDate, int startTime, int scheduleId) {
        if (applyDate == null || startTime <= 0 || scheduleId == -1) {
            return new ArrayList<>();
        }
        return applyMapper.getApplyListByDurationAndIsNotMe(applyDate, startTime, scheduleId);
    }

    @Override
    public ApplyPo getPeddingApplyById(int id) {
        if (id <= 0) {
            log.error("getPeddingApplyById service param id:[{}] is error", id);
            return null;
        }
        return applyMapper.getByIdAndIsPass(id, 0);
    }

    @Override
    public List<ApplyQueryPo> getApplyQuery(long bigoID) {
        if (bigoID <= 0) {
            log.error("getApplyQuery service param bigoID:[{}] is error", bigoID);
            return new ArrayList<>();
        }
        return applyMapper.getApplyQueryByBigoID(bigoID);
    }

    @Override
    public int isAlreadyApply(ApplyVo applyVo) {
        return applyMapper.isAlreadyApply(applyVo.getBigoID(), applyVo.getApplyDate(), applyVo.getStartTime(), applyVo.getDuration());
    }
}
