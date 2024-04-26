package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.impl.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers(){
        List<UserEntity> userEntityList=userRepository.findAll();
        log.info("出参："+userEntityList);
        return userEntityList;
    };

}
