package com.artifact.users.service.mapper;

import com.artifact.users.persistence.domain.User;
import com.artifact.users.persistence.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private final UserMapper mapper = UserMapper.INSTANCE;

    @Test
    void toDto_convertsUuidToString() {
        UUID id = UUID.randomUUID();
        User user = new User(id, "John", "Doe");

        UserDto dto = mapper.toDto(user);

        assertThat(dto.getId()).isEqualTo(id.toString());
        assertThat(dto.getName()).isEqualTo("John");
        assertThat(dto.getSurname()).isEqualTo("Doe");
    }

    @Test
    void toEntity_convertsStringToUuid() {
        UUID id = UUID.randomUUID();
        UserDto dto = new UserDto(id.toString(), "John", "Doe");

        User user = mapper.toEntity(dto);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getName()).isEqualTo("John");
        assertThat(user.getSurname()).isEqualTo("Doe");
    }

    @Test
    void toEntity_withNullId_setsNullId() {
        UserDto dto = new UserDto(null, "John", "Doe");

        User user = mapper.toEntity(dto);

        assertThat(user.getId()).isNull();
    }
}
