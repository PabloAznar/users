package com.artifact.users.service;

import com.artifact.users.persistence.UserJpaRepository;
import com.artifact.users.persistence.dto.UserDto;
import com.artifact.users.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDto findById(String id) {
        return userMapper.toDto(userJpaRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    public UserDto create(UserDto user) {
        return userMapper.toDto(userJpaRepository.save(userMapper.toEntity(user)));
    }
}
