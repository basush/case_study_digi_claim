package com.casestudy.digi_claim.service;

import com.casestudy.digi_claim.repository.UserInfoRepository;
import com.casestudy.digi_claim.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository repository;


    @Autowired
    @Lazy
    private PasswordEncoder encoder;

    public UserInfo findUserInfoDetails(String username)  {
        Optional<UserInfo> userDetail = repository.findByEmail(username); // Assuming 'email' is used as username

        return userDetail.get();
    }

    public String addUser(UserInfo userInfo) {
        // Encode password before saving the user
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);

        //send notification to the user

        return "User Added Successfully";
    }
}
