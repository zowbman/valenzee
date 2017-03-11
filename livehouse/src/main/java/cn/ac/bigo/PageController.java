package cn.ac.bigo;

import cn.ac.bigo.base.cache.ApplyDateListCache;
import cn.ac.bigo.base.util.ConcurrentDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by zwb on 2017/2/27.页面controller
 */
@Controller
public class PageController {

    @Autowired
    ApplyDateListCache applyDateListCache;

    /**
     * 排班申请
     *
     * @return
     */
    @GetMapping("/apply")
    public String apply(Model model) {
        model.addAttribute("applyDateList", applyDateListCache.getApplyDateList());
        return "frontend/apply";
    }

    /**
     * 申请排班查询
     *
     * @return
     */
    @GetMapping("/slotQuery")
    public String slotQuery() {
        return "frontend/slot_query";
    }

    /**
     * 今天排班
     *
     * @return
     */
    @GetMapping("/todaySchedule")
    public String todaySchedule() {
        return "frontend/today_schedule";
    }

    /**
     * 排班页面
     *
     * @return
     */
    @GetMapping("/schedule")
    public String schedule(Model model) {
        model.addAttribute("date", applyDateListCache.getApplyDateList());
        model.addAttribute("active", 0);
        model.addAttribute("currentDate", ConcurrentDateUtil.format(System.currentTimeMillis()));
        return "backend/schedule";
    }

    /**
     * 白名单管理页面
     *
     * @return
     */
    @GetMapping("/whiteList")
    public String whiteList(Model model) {
        model.addAttribute("active", 1);
        return "backend/white_list";
    }
}
