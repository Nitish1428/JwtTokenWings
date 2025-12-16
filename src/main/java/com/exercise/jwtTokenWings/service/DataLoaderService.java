package com.exercise.jwtTokenWings.service;

import com.exercise.jwtTokenWings.entity.UserInfo;
import com.exercise.jwtTokenWings.entity.UserRole;
import com.exercise.jwtTokenWings.repository.UserInfoRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataLoaderService {

    @Autowired
    private UserInfoRepo userInfoRepo ;

    @PostConstruct
    public void userDataLoader(){
        userInfoRepo.save(new UserInfo("jack", "password1", UserRole.CONSUMER));
        userInfoRepo.save(new UserInfo("mack", "password2", UserRole.SELLER));
        userInfoRepo.save(new UserInfo("sparrow", "password3", UserRole.CONSUMER));
        userInfoRepo.save(new UserInfo("marrow", "password4", UserRole.SELLER));
    }

}
