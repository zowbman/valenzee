package cn.ac.bigo.base.controller;

import cn.ac.bigo.base.helper.CodeHelper;
import cn.ac.bigo.base.model.po.PubReturnMsg;
import cn.ac.bigo.base.model.po.StartTimePo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zwb on 2017/2/27.字典
 */
@Controller
@RequestMapping("/dict/json")
public class DictController extends BaseController {

    /**
     * 获取开始时间
     * @return
     */
    @GetMapping("/v1/getStartTime")
    @ResponseBody
    public PubReturnMsg getStartTime() {
        List<StartTimePo> startTimePos = iStartTimeService.findAll();
        return new PubReturnMsg(CodeHelper.SUCCESS, startTimePos);
    }
}
