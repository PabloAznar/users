package com.artifact.users.service;

import com.artifact.users.persistence.UserJpaRepository;
import com.artifact.users.persistence.domain.User;
import com.artifact.users.persistence.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserJpaRepository userJpaRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UUID userId;
    private User user;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        user = new User(userId, "John", "Doe");
    }

    @Test
    void findById_returnsUserDto() {
        when(userJpaRepository.findById(userId)).thenReturn(Optional.of(user));

        UserDto result = userService.findById(userId.toString());

        assertThat(result.getId()).isEqualTo(userId.toString());
        assertThat(result.getName()).isEqualTo("John");
        assertThat(result.getSurname()).isEqualTo("Doe");
    }

    @Test
    void findById_throwsWhenNotFound() {
        when(userJpaRepository.findById(userId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findById(userId.toString()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User not found");
    }

    @Test
    void create_savesAndReturnsUserDto() {
        when(userJpaRepository.save(any(User.class))).thenReturn(user);

        UserDto input = new UserDto(null, "John", "Doe");
        UserDto result = userService.create(input);

        assertThat(result.getId()).isEqualTo(userId.toString());
        assertThat(result.getName()).isEqualTo("John");
        assertThat(result.getSurname()).isEqualTo("Doe");
    }
}
