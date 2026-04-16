package com.artifact.users.controller;

import com.artifact.users.persistence.dto.UserDto;
import com.artifact.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable String id) {
        log.info("Getting user with id: {}", id);
        return userService.findById(id);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto user) {
        log.info("Creating user: {}", user);
        return userService.create(user);
    }
}
