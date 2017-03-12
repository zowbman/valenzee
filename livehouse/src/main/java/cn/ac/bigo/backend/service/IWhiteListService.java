package cn.ac.bigo.backend.service;

import cn.ac.bigo.backend.model.po.WhiteListPo;
import cn.ac.bigo.backend.model.vo.WhiteListVo;

import java.util.List;

/**
 * Created by zwb on 2017/2/28.
 */
public interface IWhiteListService {

    List<WhiteListPo> getWhiteList();

    boolean addWhiteList(WhiteListVo whiteListVo);

    int isExist(long bigoID);
}
