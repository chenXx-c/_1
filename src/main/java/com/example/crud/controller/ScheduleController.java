package com.example.crud.controller;

import com.example.crud.entity.Schedule;
import com.example.crud.entity.User;
import com.example.crud.mapper.ScheduleMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ScheduleController {

    @Resource
    ScheduleMapper scheduleMapper;

    @PostMapping("/schedule/save")
    public ResponseEntity<Void> save(@RequestBody Schedule schedule) {
        scheduleMapper.save(schedule);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/schedule/info")
    public List<Schedule> list(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return scheduleMapper.getAll(user.getId());
    }

}
