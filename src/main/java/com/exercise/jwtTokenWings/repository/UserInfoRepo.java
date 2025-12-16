package com.exercise.jwtTokenWings.repository;

import com.exercise.jwtTokenWings.entity.UserInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {

    @Override
    Optional<UserInfo> findById(Long aLong);

     Optional<UserInfo> findByUsername(String username);
}
