package com.thoughtworks.capability.gtb.basicquiz.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.basicquiz.entity.Education;
import com.thoughtworks.capability.gtb.basicquiz.entity.User;
import com.thoughtworks.capability.gtb.basicquiz.exception.CommonException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResumeController.class)
@AutoConfigureMockMvc
public class ResumeControllerTest {
    @MockBean
    ResumeService resumeService;
    @Autowired
    private MockMvc mockMvc;

    private User firstUser;
    private Education firstUserEducation;
    @BeforeEach
    public void beforeEach(){
        firstUser = User.builder()
                .id(1l)
                .age(18l)
                .avatar("http://...")
                .description("hello world")
                .name("zh")
                .build();
        firstUserEducation = Education.builder()
                .user(firstUser)
                .year(1999)
                .title("###########")
                .description("#############.")
                .build();
    }

    @Test
    public void should_return_user_by_id_when_user_id_exist() throws Exception {
        firstUser = User.builder()
                .id(1l)
                .age(18l)
                .avatar("http://...")
                .description("hello world")
                .name("zh")
                .build();
        when(resumeService.getUser(1L)).thenReturn(firstUser);

        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("zh")));

        verify(resumeService).getUser(1L);
    }


    @Test
    public void should_return_User_NOT_FOUND_when_user_id_not_exist() throws Exception {
        when(resumeService.getUser(1L)).thenThrow(new CommonException("User Not Found"));

        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("zh")));

        verify(resumeService).getUser(1L);
    }
    @Test
    public void should_return_user_when_user_add_success() throws Exception {
        when(resumeService.addUser(firstUser)).thenReturn(firstUser);
        ObjectMapper objectMapper = new ObjectMapper();
        String josnUser = objectMapper.writeValueAsString(firstUser);

        mockMvc.perform(post("/users")
                .content(josnUser).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("zh")));

        verify(resumeService).addUser(firstUser);
    }

    @Test
    public void should_return_user_educations_when_user_id_exist() throws Exception {
        List<Education> educations = Collections.singletonList(firstUserEducation);
        when(resumeService.getEducations(1)).thenReturn(educations);

        mockMvc.perform(get("/users/"+firstUser.getId()+"/educations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].year", is(1999)));

        verify(resumeService).getEducations(1);
    }
}