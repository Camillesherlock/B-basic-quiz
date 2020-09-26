package com.thoughtworks.capability.gtb.basicquiz.service;

import com.thoughtworks.capability.gtb.basicquiz.dto.EducationDTO;
import com.thoughtworks.capability.gtb.basicquiz.entity.Education;
import com.thoughtworks.capability.gtb.basicquiz.entity.User;
import com.thoughtworks.capability.gtb.basicquiz.exception.UserNotExistException;
import com.thoughtworks.capability.gtb.basicquiz.repository.EducationRepository;
import com.thoughtworks.capability.gtb.basicquiz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.naming.CommunicationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EducationServiceTest {

    private EducationService educationService;

    private Education education;

    private EducationDTO educationDTO;

    @Mock
    private EducationRepository educationRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        educationService = new EducationService(educationRepository, userRepository);
        education = Education.builder()
                .id(1L).title("education 1").description("education test")
                .year(2000).build();
        educationDTO = EducationDTO.builder()
                .title("education 1").description("education test")
                .year(2000L).build();
    }

    @Nested
    class GetEducationByUserId {
        @Test
        public void should_return_education_list_when_user_exist() throws CommunicationException {
            List<Education> educations = new ArrayList<>();
            educations.add(education);
            when(userRepository.existsById(1L)).thenReturn(true);
            when(educationRepository.findAllByUserId(1L)).thenReturn(educations);
            List<EducationDTO> result = educationService.getEducationByUserId(1L);
            assertEquals(1, result.size());
            assertEquals("education 1", result.get(0).getTitle());
        }


        @Nested
        class SaveEducation {
            @Test
            public void should_return_educationDTO_when_save_education() throws CommunicationException {
                User user = User.builder().id(1L).age(25L)
                        .name("11").avatar("http://..")
                        .description("education test").build();
                Optional<User> user1 = Optional.of(user);
                when(userRepository.findById(1L)).thenReturn(user1);
                education.setDescription("saved education");
                when(educationRepository.save(any())).thenReturn(education);
                EducationDTO educationDTO = educationService
                        .saveEducation(1L, EducationServiceTest.this.educationDTO);
                assertEquals("saved education", educationDTO.getDescription());
            }

            @Test
            public void should_throw_exception_when_user_is_not_exist() {
                Optional<User> user = Optional.empty();
                when(userRepository.findById(1L)).thenReturn(user);
                UserNotExistException userNotExistException = assertThrows(UserNotExistException.class,
                        () -> educationService.saveEducation(1L, educationDTO),
                        "Expected doThing() to throw, but it didn't");
                assertEquals("user is not exist",
                        userNotExistException.getMessage());
            }
        }
    }
}
