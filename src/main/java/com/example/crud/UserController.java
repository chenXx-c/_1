package com.example.crud;

import com.example.crud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {


    @Resource
    UserMapper userMapper;

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        User dbUser = userMapper.getUserByNameAndPassword(user.getName(), user.getPassword());
        return dbUser;
    }

}
