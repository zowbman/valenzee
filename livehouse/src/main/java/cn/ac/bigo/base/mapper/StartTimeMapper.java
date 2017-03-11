package cn.ac.bigo.base.mapper;

import cn.ac.bigo.base.model.po.StartTimePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zwb on 2017/2/27.
 */
@Mapper
public interface StartTimeMapper {

    @Select("SELECT id,startTime FROM t_startTime")
    List<StartTimePo> findAll();

    @Select("SELECT startTime FROM t_startTime WHERE id = #{id}")
    String getStartTimeById(@Param("id") int id);
}
