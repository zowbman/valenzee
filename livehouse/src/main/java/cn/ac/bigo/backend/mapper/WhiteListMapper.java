package cn.ac.bigo.backend.mapper;

import cn.ac.bigo.backend.model.po.WhiteListPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zwb on 2017/2/28.
 */
@Mapper
public interface WhiteListMapper {

    @Select("select addTime, bigoID, fraction from t_whitelist")
    List<WhiteListPo> findAll();

    @Insert("insert ignore into t_whitelist (bigoID, fraction, addTime) values(#{bigoID}, #{fraction}, #{addTime})")
    boolean add(@Param("bigoID") long bigoID, @Param("fraction") int fraction, @Param("addTime") int addTime);
}
