package cn.ac.bigo.base.service.impl;

import cn.ac.bigo.base.mapper.StartTimeMapper;
import cn.ac.bigo.base.model.po.StartTimePo;
import cn.ac.bigo.base.service.IStartTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zwb on 2017/2/27.
 */
@Service
public class StartTimeServiceImpl implements IStartTimeService {
    @Autowired
    StartTimeMapper startTimeMapper;

    @Override
    public List<StartTimePo> findAll() {
        return startTimeMapper.findAll();
    }

    @Override
    public String getStartTimeById(int id) {
        return startTimeMapper.getStartTimeById(id);
    }
}
