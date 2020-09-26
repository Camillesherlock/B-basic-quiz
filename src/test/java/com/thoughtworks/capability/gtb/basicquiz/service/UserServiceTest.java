package com.thoughtworks.capability.gtb.basicquiz.service;

import static org.junit.jupiter.api.Assertions.*;

import com.thoughtworks.capability.gtb.basicquiz.dto.UserDTO;
import com.thoughtworks.capability.gtb.basicquiz.entity.User;
import com.thoughtworks.capability.gtb.basicquiz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;

    private User user;

    private UserDTO userDTO;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        userService = new UserService(userRepository);
        user = User.builder()
                .id(1L).name("11").age(25L)
                .avatar("http://...").description("user test")
                .build();
        userDTO = UserDTO.builder()
                .age(25L).avatar("http://...")
                .id(1L).name("11")
                .description("user test").build();
    }

    @Nested
    class GetUserById {
        @Test
        public void should_return_user_when_get_user_by_id() {
            Optional<User> user1 = Optional.of(user);
            when(userRepository.findById(1L)).thenReturn(user1);
            UserDTO userDTO = userService.getUserById(1L);
            assertEquals("11", userDTO.getName());
            assertEquals("http://...", userDTO.getAvatar());
            assertEquals("user test", userDTO.getDescription());
        }
    }

    @Nested
    class SaveUser {
        @Test
        public void should_return_user_when_save_user() {
            user.setDescription("saved user");
            when(userRepository.save(any())).thenReturn(user);
            UserDTO userDTO = userService.saveUser(UserServiceTest.this.userDTO);
            assertEquals("saved user", userDTO.getDescription());
        }
    }
}
