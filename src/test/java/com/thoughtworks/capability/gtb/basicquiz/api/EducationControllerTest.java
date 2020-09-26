package com.thoughtworks.capability.gtb.basicquiz.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.basicquiz.dto.EducationDTO;
import com.thoughtworks.capability.gtb.basicquiz.service.EducationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EducationController.class)
@AutoConfigureJsonTesters
class EducationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EducationService educationService;

    @Autowired
    private JacksonTester<EducationDTO> jacksonTester;

    private ObjectMapper objectMapper = new ObjectMapper();

    private EducationDTO educationDTO;

    @BeforeEach
    public void beforeEach() {
        educationDTO = EducationDTO.builder()
                .title("education 1").year(2020L)
                .description("education test").build();
    }

    @Nested
    class GetEducation {
        @Test
        public void should_return_education_by_user_id() throws Exception {
            List<EducationDTO> educations = new ArrayList<>();
            educations.add(educationDTO);
            when(educationService.getEducationByUserId(1L)).thenReturn(educations);
            mockMvc.perform(get("/users/{id}/educations", 1L))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].title", is("education 1")));
            verify(educationService).getEducationByUserId(1L);
        }

        @Test
        public void should_throw_exception_when_user_id_is_not_number() throws Exception {
            List<EducationDTO> educations = new ArrayList<>();
            educations.add(educationDTO);
            when(educationService.getEducationByUserId(1L)).thenReturn(educations);
            mockMvc.perform(get("/users/{id}/educations", "error id"))
                    .andExpect(status().isBadRequest());
            verify(educationService, times(0)).getEducationByUserId(1L);
        }
    }

    @Nested
    class CreateEducation {

        @Nested
        class NormalCase {
            @Test
            public void should_return_education_dto_when_save_education() throws Exception {
                when(educationService.saveEducation(1L, educationDTO))
                        .thenReturn(educationDTO);
                mockMvc.perform(post("/users/{id}/educations", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(educationDTO)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.year", is(2020)))
                        .andExpect(jsonPath("$.title", is("education 1")))
                        .andExpect(jsonPath("$.description", is("education test")));
                verify(educationService).saveEducation(1L, educationDTO);
            }
        }

        @Nested
        class InvalidParam {
            @Test
            public void should_throw_exception_when_year_is_less_than_1900() throws Exception {
                educationDTO.setYear(1899L);
                when(educationService.saveEducation(1L, educationDTO))
                        .thenReturn(educationDTO);
                mockMvc.perform(post("/users/{id}/educations", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(educationDTO)))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.status", is(400)))
                        .andExpect(jsonPath("$.error", is("Bad Request")))
                        .andExpect(jsonPath("$.message", is("must be greater than or equal to 1900")));
                verify(educationService, times(0)).getEducationByUserId(1L);
            }

            @Test
            public void should_throw_exception_when_year_is_larger_than_2020() throws Exception {
                educationDTO.setYear(2021L);
                when(educationService.saveEducation(1L, educationDTO))
                        .thenReturn(educationDTO);
                mockMvc.perform(post("/users/{id}/educations", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(educationDTO)))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.status", is(400)))
                        .andExpect(jsonPath("$.error", is("Bad Request")))
                        .andExpect(jsonPath("$.message", is("must be less than or equal to 2020")));
                verify(educationService, times(0)).getEducationByUserId(1L);
            }

            @Test
            public void should_throw_exception_when_title_is_null() throws Exception {
                educationDTO.setTitle(null);
                when(educationService.saveEducation(1L, educationDTO))
                        .thenReturn(educationDTO);
                mockMvc.perform(post("/users/{id}/educations", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(educationDTO)))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.status", is(400)))
                        .andExpect(jsonPath("$.error", is("Bad Request")))
                        .andExpect(jsonPath("$.message", is("must not be empty")));
                verify(educationService, times(0)).getEducationByUserId(1L);
            }

            @Test
            public void should_throw_exception_when_description_is_null() throws Exception {
                educationDTO.setDescription(null);
                when(educationService.saveEducation(1L, educationDTO))
                        .thenReturn(educationDTO);
                mockMvc.perform(post("/users/{id}/educations", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(educationDTO)))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.status", is(400)))
                        .andExpect(jsonPath("$.error", is("Bad Request")))
                        .andExpect(jsonPath("$.message", is("must not be empty")));
                verify(educationService, times(0)).getEducationByUserId(1L);
            }
        }
    }

}