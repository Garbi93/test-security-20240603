package com.example.testsecurity20240603.repository;

import com.example.testsecurity20240603.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    // DB 에 동일 유저이름이 존재하는지 확인하기 위해
    // JPA 구문을 강제로 커스텀 하여 생성
    // 동일한 값이 존재하면 true 리턴 없으면 false 를 리턴
    boolean existsByUsername(String username);

    // 회원 이름 기준으로 해당 회원 정보 갖고 오기
    UserEntity findByUsername(String username);

}
