package cn.ac.bigo.backend.service.impl;

import cn.ac.bigo.backend.mapper.WhiteListMapper;
import cn.ac.bigo.backend.model.po.WhiteListPo;
import cn.ac.bigo.backend.model.vo.WhiteListVo;
import cn.ac.bigo.backend.service.IWhiteListService;
import cn.ac.bigo.base.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zwb on 2017/2/28.
 */
@Service
public class WhiteListServiceImpl implements IWhiteListService {

    @Autowired
    WhiteListMapper whiteListMapper;

    @Override
    public List<WhiteListPo> getWhiteList() {
        return whiteListMapper.findAll();
    }

    @Override
    public boolean addWhiteList(WhiteListVo whiteListVo) {
        return whiteListMapper.add(whiteListVo.getBigoID(), whiteListVo.getFraction(), BaseUtil.currentTimeMillis());
    }

    @Override
    public int isExist(long bigoID) {
        if (bigoID <= 0) {
            return 0;
        }
        return whiteListMapper.isExist(bigoID);
    }
}
