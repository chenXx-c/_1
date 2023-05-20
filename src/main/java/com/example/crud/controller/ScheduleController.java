package com.example.crud.controller;

import com.example.crud.entity.Schedule;
import com.example.crud.entity.User;
import com.example.crud.mapper.ScheduleMapper;
import com.example.crud.service.ScheduleService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ScheduleController {

    @Resource
    ScheduleMapper scheduleMapper;

    @Resource
    ScheduleService scheduleService;

    @PostMapping("/schedule/save")
    public ResponseEntity<Void> save(@RequestBody Schedule schedule, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);  // 401状态码
        }
        if (schedule.getDate() == null || schedule.getName() == null || schedule.getName().equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        schedule.setUserId(user.getId());
        scheduleMapper.save(schedule);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/schedule/info")
    public List<Schedule> getUerSchedule() {
        return scheduleService.getUerSchedule();
    }

    @DeleteMapping("/schedule/{id}")   // {id}  ->  /schedule/1
    public ResponseEntity<Void> save(@PathVariable Integer id) {
        scheduleMapper.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
