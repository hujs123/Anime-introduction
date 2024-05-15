package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        log.info("参数出参：调用到接口");
        return "hello调用成功";
    }
    //

    @PostMapping("getUsers")
    public UserEntity getUsers(@RequestBody Map<String, String> requestBody) {
        return userService.getUsers(requestBody);
    }

    @PostMapping("getAllUsers")
    public List<UserEntity>getAllUsers(){
        return userService.getAllUsers();
    }
}
