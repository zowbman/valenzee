package cn.ac.bigo.base.service;


import cn.ac.bigo.base.model.po.StartTimePo;

import java.util.List;

/**
 * Created by zwb on 2017/2/27.
 */
public interface IStartTimeService {

    /**
     * 查询全部开始时间
     *
     * @return
     */
    List<StartTimePo> findAll();

    /**
     * 根据id查询开始时间
     *
     * @param id
     * @return
     */
    String getStartTimeById(int id);

    /**
     * @return
     */
    List<String> getStartTime();
}
