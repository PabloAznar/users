package com.artifact.users.service;

import com.artifact.users.persistence.dto.UserDto;

public interface UserService {

    UserDto findById(String id);

    UserDto create(UserDto user);

}
