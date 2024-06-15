package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/hello")
    public String hello() {
        userService.getPixiv();
        log.info("【调用接口】:api/users/hello");
        return "api/users/hello调用成功";
    }

    @GetMapping("getUsers")
    public List<UserEntity> getUsers() throws Exception {
        log.info("【调用接口】:api/users/getUsers");
        return userService.getUsers();
    }

    @PostMapping("getAllUsers")
    public List<UserEntity>getAllUsers(){
        return userService.getAllUsers();
    }
}
