package cn.ac.bigo.frontend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by zwb on 2017/2/27.
 */
@Mapper
public interface ApplyMapper {
    @Insert("insert ignore into t_apply (bigoID, whatsAppNumber, applyDate, startTime, duration, addTime) values(#{bigoID}, #{whatsAppNumber}, #{applyDate}, #{startTime}, #{duration}, #{addTime})")
    boolean add(@Param("bigoID") long bigoID, @Param("whatsAppNumber") String whatsAppNumber, @Param("applyDate") Date applyDate, @Param("startTime") int startTime, @Param("duration") int duration, @Param("addTime") int addTime);
}
