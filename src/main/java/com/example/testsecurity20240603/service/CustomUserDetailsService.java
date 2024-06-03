package com.example.testsecurity20240603.service;

import com.example.testsecurity20240603.dto.CustomUserDetails;
import com.example.testsecurity20240603.entity.UserEntity;
import com.example.testsecurity20240603.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByUsername(username);

        // 해당 회원이 비어있지 않다면 로그인 진행
        if (userData != null) {
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
