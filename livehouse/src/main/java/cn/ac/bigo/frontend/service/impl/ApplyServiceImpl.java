package cn.ac.bigo.frontend.service.impl;

import cn.ac.bigo.base.util.BaseUtil;
import cn.ac.bigo.frontend.mapper.ApplyMapper;
import cn.ac.bigo.frontend.model.vo.ApplyVo;
import cn.ac.bigo.frontend.service.IApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zwb on 2017/2/27.
 */
@Service
public class ApplyServiceImpl implements IApplyService {
    @Autowired
    ApplyMapper applyMapper;

    @Override
    public boolean apply(ApplyVo applyVo) {
        return applyMapper.add(applyVo.getBigoID(), applyVo.getWhatsAppNumber(), applyVo.getApplyDate(), applyVo.getStartTime(), applyVo.getDuration(), BaseUtil.currentTimeMillis());
    }
}
