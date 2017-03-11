package cn.ac.bigo.backend.mapper;

import cn.ac.bigo.backend.model.po.SchedulePo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by zwb on 2017/3/6.
 */
@Mapper
public interface ScheduleMapper {
    @Select("select id,bigoID,whatsAppNumber,timeSlot from t_schedule where date=#{date}")
    List<SchedulePo> getScheduleInfoByDate(@Param("date") String date);

    @Select("select timeSlot from t_schedule where date=#{date}")
    List<String> getTimeSlotListByDate(@Param("date") Date date);

    @Select("select timeSlot from t_schedule where date=#{date} and id!=#{id}")
    List<String> getTimeSlotListByDateAndIsNotMe(@Param("date") Date date, @Param("id") int id);

    @Insert("insert ignore into t_schedule(bigoID,whatsAppNumber,timeSlot,date,applyId) values(#{bigoID},#{whatsAppNumber},#{timeSlot},#{date},#{applyId})")
    boolean saveScheduleInfo(@Param("bigoID") long bigoID, @Param("whatsAppNumber") String whatsAppNumber, @Param("timeSlot") String timeSlot, @Param("date") String date, @Param("applyId") int applyId);

    @Select("select applyId from t_schedule where id=#{id}")
    int getApplyIdById(@Param("id") int id);

    @Delete("delete from t_schedule where id=#{id}")
    boolean delScheduleById(@Param("id") int id);
}
