package com.example.testsecurity20240603.repository;

import com.example.testsecurity20240603.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
