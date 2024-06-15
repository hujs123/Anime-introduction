package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.impl.UserRepository;

import com.example.demo.thirdInterface.thireInterface;
import com.example.demo.utils.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncryptionUtils encryptionUtils;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private thireInterface thireInterface;

    public List<UserEntity> getUsers(){
        List<UserEntity> userEntityList=userMapper.queryUsers();
        return userEntityList;
    };

    public List<UserEntity> getAllUsers(){
        List<UserEntity> userEntityList=userRepository.findAll();
        log.info("出参："+userEntityList);
        return userEntityList;
    };

    public String getPixiv(){
        thireInterface.thirdServerInterface(null,null,null);
        return "OK";
    };

}
