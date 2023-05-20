package com.example.crud.service;

import com.example.crud.entity.Schedule;
import com.example.crud.entity.User;
import com.example.crud.mapper.ScheduleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleService {

    @Resource
    HttpServletRequest request;

    @Resource
    ScheduleMapper scheduleMapper;

    public List<Schedule> getUerSchedule() {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return Collections.emptyList();
        }
        List<Schedule> all = scheduleMapper.getAll(user.getId());
        for (Schedule schedule : all) {
            Date date = new Date();
            int between = 0;
            long betweenMilli = schedule.getDate().getTime() - date.getTime();  // 日程时间 - 当前时间
            long between1 = betweenMilli / (24 * 3600 * 1000);
            String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);
            String scheduleDateFormat = new SimpleDateFormat("yyyy-MM-dd").format(schedule.getDate());
            between = (int) between1;
            if (!dateFormat.equals(scheduleDateFormat)) {  // 不是同一天
                if (betweenMilli > 0) {
                    between += 1;
                }
                if (between1 == 0 && betweenMilli < 0) {
                    between = -1;
                }
            }
            schedule.setBetween(between);
        }
        return all;
    }

}
