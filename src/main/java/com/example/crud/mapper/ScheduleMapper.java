package com.example.crud.mapper;

import com.example.crud.entity.Schedule;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ScheduleMapper {

    @Insert("INSERT INTO `schedule`(name, description, date) VALUES(#{name}, #{description}, #{date})")
    void save(Schedule schedule);


    @Select("SELECT * FROM `schedule` where user_id = #{userId} ORDER BY id desc")
    List<Schedule> getAll(@Param("userId") Integer userId);

}
