package com.example.crud.mapper;

import com.example.crud.entity.Schedule;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ScheduleMapper {

    @Delete("DELETE from `schedule` WHERE id = #{id}")
    void delete(@Param("id") Integer id);

    @Insert("INSERT INTO `schedule`(name, description, date, user_id) VALUES(#{name}, #{description}, #{date}, #{userId})")
    void save(Schedule schedule);


    @Select("SELECT * FROM `schedule` where user_id = #{userId} ORDER BY id desc")
    List<Schedule> getAll(@Param("userId") Integer userId);

}
