package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.impl.UserRepository;
import com.example.demo.utils.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserService  {

    private static final String SECRET_KEY = "your-secret-key"; // 请确保这与前端使用的密钥匹配
    @Autowired
    private UserRepository userRepository;

    public UserEntity getUsers(Map<String, String>  requestBody){
        String encryptedData = requestBody.get("encryptedData");
        log.info("requestBody:"+requestBody);
        String decryptedData = EncryptionUtils.decryptData(encryptedData, SECRET_KEY);
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
