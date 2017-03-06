package cn.ac.bigo.base.controller;

import cn.ac.bigo.backend.service.IWhiteListService;
import cn.ac.bigo.base.service.IStartTimeService;
import cn.ac.bigo.frontend.service.IApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by zwb on 2017/2/27.
 */
@Controller
public class BaseController {
    @Autowired
    protected IApplyService iApplyService;

    @Autowired
    protected IStartTimeService iStartTimeService;

    @Autowired
    protected IWhiteListService iWhiteListService;
}
