package com.exercise.jwtTokenWings.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class UserInfo {
    public UserInfo(String username, String password, UserRole roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public  UserRole getRoles() {
        return roles;
    }

    public void setRoles(UserRole roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long userId ;

    @NotBlank
    private String username ;

    @NotBlank
    private String password ;

    @Enumerated(EnumType.STRING)
    private UserRole roles;
}
