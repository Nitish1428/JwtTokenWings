package com.exercise.jwtTokenWings.controller;

import com.exercise.jwtTokenWings.dto.AuthRequest;
import com.exercise.jwtTokenWings.dto.SignUpDTO;
import com.exercise.jwtTokenWings.service.CustomUserDetailsService;
import com.exercise.jwtTokenWings.service.JwtServiceClass;
import com.exercise.jwtTokenWings.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private LoginService loginService ;


    @PostMapping("/login")
    public ResponseEntity<?> authLogin(@RequestBody AuthRequest authRequest){

        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
            authenticationManager.authenticate(authentication);
           return  loginService.loginService(authRequest);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO signUpDTO){

        try {
            return loginService.signUp(signUpDTO);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
