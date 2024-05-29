package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.impl.UserRepository;

import com.example.demo.utils.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncryptionUtils encryptionUtils;

    public UserEntity getUsers(Map<String, String>  requestBody) throws Exception {
        String encryptedData = requestBody.get("encryptedData");
        log.info("requestBody:"+requestBody);
        log.info("encryptedData:"+encryptedData);
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        log.info("iv:",iv);
        String decryptedData = encryptionUtils.decryptdata(encryptedData,"qazwsxedcrfv");
        log.info("decryptedData:"+decryptedData);
        UserEntity userEntity=userRepository.findByName(decryptedData);
        log.info("userEntity:"+userEntity);
        log.info("出参："+userEntity);
        return userEntity;
    };

    public List<UserEntity> getAllUsers(){
        List<UserEntity> userEntityList=userRepository.findAll();
        log.info("出参："+userEntityList);
        return userEntityList;
    };

}
