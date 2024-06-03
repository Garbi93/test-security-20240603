package com.example.testsecurity20240603.service;

import com.example.testsecurity20240603.dto.JoinDTO;
import com.example.testsecurity20240603.entity.UserEntity;
import com.example.testsecurity20240603.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;

    // 비밀번호 해쉬화 하여 저장 시키기 위해 주입 받음
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {

        // db에 동일한 username을 가진 회원이 있는지 검증부터 해야함


        // 동일 정보가 존재하지 않으면 저장 진행하기
        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        // 비밀번호 해쉬화 하여 저장 시키기
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}
