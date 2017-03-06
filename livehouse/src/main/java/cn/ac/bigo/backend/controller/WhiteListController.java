package cn.ac.bigo.backend.controller;

import cn.ac.bigo.backend.model.po.WhiteListPo;
import cn.ac.bigo.backend.model.vo.WhiteListVo;
import cn.ac.bigo.base.controller.BaseController;
import cn.ac.bigo.base.helper.CodeHelper;
import cn.ac.bigo.base.model.po.PubReturnMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zwb on 2017/2/28.
 */
@Controller
@RequestMapping("/backend/json")
public class WhiteListController extends BaseController {

    /**
     * 获取白名单列表
     *
     * @return
     */
    @GetMapping("/v1/getWhiteList")
    @ResponseBody
    public PubReturnMsg getWhiteList() {
        List<WhiteListPo> whiteListPos = iWhiteListService.getWhiteList();
        return new PubReturnMsg(CodeHelper.SUCCESS, whiteListPos);
    }

    /**
     * 添加白名单
     *
     * @param whiteListVo
     * @return
     */
    @PostMapping("/v1/addWhiteList")
    @ResponseBody
    public PubReturnMsg addWhiteList(WhiteListVo whiteListVo) {
        boolean result = iWhiteListService.addWhiteList(whiteListVo);
        return new PubReturnMsg(CodeHelper.SUCCESS);
    }
}
