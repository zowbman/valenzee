package cn.ac.bigo.frontend.controller;

import cn.ac.bigo.base.cache.StartTimeCache;
import cn.ac.bigo.base.controller.BaseController;
import cn.ac.bigo.base.helper.CodeHelper;
import cn.ac.bigo.base.model.po.PubReturnMsg;
import cn.ac.bigo.base.util.ConcurrentDateUtil;
import cn.ac.bigo.frontend.model.po.ApplyQueryPo;
import cn.ac.bigo.frontend.model.po.ApplyQueryShowPo;
import cn.ac.bigo.frontend.model.vo.ApplyVo;
import org.mockito.internal.util.collections.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by zwb on 2017/2/27.
 */
@Controller
@RequestMapping("/frontend/json")
public class ApplyController extends BaseController {

    private Logger log = LoggerFactory.getLogger(getClass());

    private static List<String> APPLYSTATUS = Arrays.asList("待处理", "失败", "成功");

    @Autowired
    StartTimeCache startTimeCache;

    @PostMapping("/v1/applySubmit")
    @ResponseBody
    public PubReturnMsg applySubmit(ApplyVo applyVo) {
        log.info("param applyVo:[{}]", applyVo.toString());
        int count = iWhiteListService.isExist(applyVo.getBigoID());
        if (count == 0) {//是否有白名单
            return new PubReturnMsg(CodeHelper.WHITELISTNOTEXIST);
        }
        count = iApplyService.isAlreadyApply(applyVo);
        if (count == 1) {//是否已经申请此时间段
            return new PubReturnMsg(CodeHelper.ALREADYAPPLY);
        }
        boolean result = iApplyService.apply(applyVo);
        if (result) {
            return new PubReturnMsg(CodeHelper.SUCCESS);
        }
        return new PubReturnMsg(CodeHelper.FAILURE);
    }

    @PostMapping("/v1/applyQuery")
    @ResponseBody
    public PubReturnMsg applyQuery(long bigoID) {
        log.info("param bigoID:[{}]", bigoID);
        if (bigoID <= 0) {
            return new PubReturnMsg(CodeHelper.PARAMS_ERROR);
        }
        List<ApplyQueryPo> applyQueryPoList = iApplyService.getApplyQuery(bigoID);
        List<ApplyQueryShowPo> applyQueryShowPoList = new ArrayList<>();
        for (ApplyQueryPo applyQueryPo : applyQueryPoList) {
            ApplyQueryShowPo applyQueryShowPo = new ApplyQueryShowPo();
            applyQueryShowPo.setAddTime(ConcurrentDateUtil.format(applyQueryPo.getAddTime() * 1000L));
            int startTime = applyQueryPo.getStartTime();
            int endTime = applyQueryPo.getDuration() == 1 ? applyQueryPo.getStartTime() : applyQueryPo.getStartTime() + 1;
            applyQueryShowPo.setApplyDate(applyQueryPo.getApplyDate() + "(" + startTimeCache.getStartTime(startTime) + "~" + startTimeCache.getStartTime(endTime) + ")");
            if (applyQueryPo.getIsPass() > 2) {
                applyQueryShowPo.setApplyStatus("未知错误");
            } else {
                applyQueryShowPo.setApplyStatus(APPLYSTATUS.get(applyQueryPo.getIsPass()));
            }
            applyQueryShowPoList.add(applyQueryShowPo);
        }
        return new PubReturnMsg(CodeHelper.SUCCESS, applyQueryShowPoList);
    }
}
