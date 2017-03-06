package cn.ac.bigo.frontend.service;

import cn.ac.bigo.frontend.model.vo.ApplyVo;

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
}
