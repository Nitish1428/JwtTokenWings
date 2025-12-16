package com.exercise.jwtTokenWings.service;

import com.exercise.jwtTokenWings.entity.UserInfo;
import com.exercise.jwtTokenWings.entity.UserRole;
import com.exercise.jwtTokenWings.repository.UserInfoRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserInfoRepo userInfoRepo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserInfo userInfo = userInfoRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("username Not Found exception"));
        Collection< ? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(userInfo.getRoles().name()));
       return User.withUsername(userInfo.getUsername())
               .password(userInfo.getPassword())
               .authorities(authorities)
               .build();
    }
}
