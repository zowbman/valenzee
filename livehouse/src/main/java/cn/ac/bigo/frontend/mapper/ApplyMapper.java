package cn.ac.bigo.frontend.mapper;

import cn.ac.bigo.backend.model.po.ApplyListByDatePo;
import cn.ac.bigo.backend.model.po.ApplyPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * Created by zwb on 2017/2/27.
 */
@Mapper
public interface ApplyMapper {
    @Insert("insert ignore into t_apply (bigoID, whatsAppNumber, applyDate, startTime, duration, addTime) values(#{bigoID}, #{whatsAppNumber}, #{applyDate}, #{startTime}, #{duration}, #{addTime})")
    boolean add(@Param("bigoID") long bigoID, @Param("whatsAppNumber") String whatsAppNumber, @Param("applyDate") Date applyDate, @Param("startTime") int startTime, @Param("duration") int duration, @Param("addTime") int addTime);

    @Select("select id,bigoID,applyDate,startTime,duration,addTime from t_apply where applyDate=#{applyDate} and startTime=#{startTime} and isPass=0")
    List<ApplyListByDatePo> getApplyListByDuration(@Param("applyDate") Date applyDate, @Param("startTime") int startTime);

    @Select("select ta.id,ta.bigoID,ta.applyDate,ta.startTime,ta.duration,ta.addTime from t_apply ta left join t_schedule ts on ta.id=ts.applyId and ts.id!=#{scheduleId} where ta.applyDate=#{applyDate} and ta.startTime=#{startTime} and ta.isPass=0")
    List<ApplyListByDatePo> getApplyListByDurationAndIsNotMe(@Param("applyDate") Date applyDate, @Param("startTime") int startTime, @Param("scheduleId") int scheduleId);

    @Select("select id,bigoID,whatsAppNumber,applyDate,startTime,duration from t_apply where id=#{id} and isPass=#{isPass}")
    ApplyPo getByIdAndIsPass(@Param("id") int id, @Param("isPass") int isPass);

    @Update("update t_apply set isPass=#{isPass} where id=#{id}")
    boolean updateIsPassById(@Param("id") int id, @Param("isPass") int isPass);
}
