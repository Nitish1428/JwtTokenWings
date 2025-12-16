package com.exercise.jwtTokenWings.service;

import com.exercise.jwtTokenWings.dto.AuthRequest;
import com.exercise.jwtTokenWings.dto.SignUpDTO;
import com.exercise.jwtTokenWings.entity.UserInfo;
import com.exercise.jwtTokenWings.entity.UserRole;
import com.exercise.jwtTokenWings.exception.InvalidRoleException;
import com.exercise.jwtTokenWings.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private JwtServiceClass jwtServiceClass;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public ResponseEntity loginService(AuthRequest authRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        String token = jwtServiceClass.createToken(userDetails.getUsername());

        Map<String, Object> jwtResponse = new HashMap<>();
        jwtResponse.put("Token", token);
        jwtResponse.put("status", HttpStatus.OK.value());

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    public ResponseEntity<?> signUp(SignUpDTO signUpDTO) {

        Optional<UserInfo> userInfo = userInfoRepo.findByUsername(signUpDTO.getUsername());
        if (userInfo.isPresent()) {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }

        UserRole userRole = validateAndGetRole(signUpDTO.getRole().toUpperCase());

        UserInfo userInfo1 = userInfoRepo.save(new UserInfo(signUpDTO.getUsername(),
                passwordEncoder.encode(signUpDTO.getPassword()),
                userRole));

        return new ResponseEntity<>(userInfo1, HttpStatus.CREATED);
    }

    public UserRole validateAndGetRole(String role) {

        if (role == null || role.isBlank()) {
            throw new InvalidRoleException("Role cannot be null or empty");
        }

        return Arrays.stream(UserRole.values())
                .filter(r -> r.name().equals(role))
                .findFirst()
                .orElseThrow(() ->
                        new InvalidRoleException(
                                "Invalid role: " + role +
                                        ". Allowed roles: " + Arrays.toString(UserRole.values())
                        )
                );
    }

}
