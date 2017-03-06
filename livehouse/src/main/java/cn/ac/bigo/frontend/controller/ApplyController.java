package cn.ac.bigo.frontend.controller;

import cn.ac.bigo.base.controller.BaseController;
import cn.ac.bigo.base.helper.CodeHelper;
import cn.ac.bigo.base.model.po.PubReturnMsg;
import cn.ac.bigo.frontend.model.vo.ApplyVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zwb on 2017/2/27.
 */
@Controller
@RequestMapping("/frontend/json")
public class ApplyController extends BaseController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping("/v1/applySubmit")
    @ResponseBody
    public PubReturnMsg applySubmit(ApplyVo applyVo){
        log.info(applyVo.toString());
        boolean result = iApplyService.apply(applyVo);
        return new PubReturnMsg(CodeHelper.SUCCESS);
    }
}
