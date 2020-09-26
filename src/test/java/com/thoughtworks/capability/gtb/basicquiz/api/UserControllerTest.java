package com.thoughtworks.capability.gtb.basicquiz.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.basicquiz.dto.UserDTO;
import com.thoughtworks.capability.gtb.basicquiz.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureJsonTesters
class UserControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<UserDTO> userJson;

    private ObjectMapper objectMapper = new ObjectMapper();

    private UserDTO userDTO;

    @BeforeEach
    public void beforeEach() {
        userDTO = UserDTO.builder()
                .id(1L).name("11")
                .age(25L).avatar("http://...")
                .description("A good boy...").build();
    }

    @Nested
    class GetUserInfo {
        @Test
        public void should_return_user_by_id() throws Exception {
            when(userService.getUserById(1L)).thenReturn(userDTO);
            mockMvc.perform(get("/users/{id}", 1L))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.name", is("11")));
            verify(userService, times(1)).getUserById(1L);
        }

    }

    @Nested
    class CreateUserInfo {
        @Test
        public void should_create_user_info() throws Exception {
            when(userService.saveUser(userDTO)).thenReturn(userDTO);
            MockHttpServletResponse response = mockMvc.perform(post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userDTO)))
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse();
            assertEquals(response.getContentAsString(),userJson.write(userDTO).getJson());
            verify(userService).saveUser(userDTO);
        }

        @Nested
        class UserParamInvalid {
            @Test
            public void should_throw_exception_when_age_is_less_than_17() throws Exception {
                when(userService.saveUser(userDTO)).thenReturn(userDTO);
                userDTO.setAge(16L);
                mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.status", is(400)))
                        .andExpect(jsonPath("$.error", is("Bad Request")))
                        .andExpect(jsonPath("$.message", is("must be greater than or equal to 17")));
            }

            @Test
            public void should_throw_exception_when_avatar_is_less_than_8() throws Exception {
                when(userService.saveUser(userDTO)).thenReturn(userDTO);
                userDTO.setAvatar("less");
                mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.status", is(400)))
                        .andExpect(jsonPath("$.error", is("Bad Request")))
                        .andExpect(jsonPath("$.message", is("size must be between 8 and 512")));
            }

            @Test
            public void should_not_throw_exception_when_description_is_null() throws Exception {
                userDTO.setDescription(null);
                when(userService.saveUser(userDTO)).thenReturn(userDTO);
                MockHttpServletResponse response = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                        .andExpect(status().isCreated())
                        .andReturn()
                        .getResponse();
                assertEquals(response.getContentAsString(),userJson.write(userDTO).getJson());
                verify(userService).saveUser(userDTO);
            }
        }
    }
}