package cn.ac.bigo.base.mapper;

import cn.ac.bigo.base.model.po.StartTimePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zwb on 2017/2/27.
 */
@Mapper
public interface StartTimeMapper {

    @Select("SELECT * FROM t_startTime")
    List<StartTimePo> findAll();
}
