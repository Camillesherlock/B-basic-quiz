package com.thoughtworks.capability.gtb.basicquiz.service;

import com.thoughtworks.capability.gtb.basicquiz.dto.EducationDTO;
import com.thoughtworks.capability.gtb.basicquiz.entity.Education;
import com.thoughtworks.capability.gtb.basicquiz.entity.User;
import com.thoughtworks.capability.gtb.basicquiz.repository.EducationRepository;
import com.thoughtworks.capability.gtb.basicquiz.repository.UserRepository;
import com.thoughtworks.capability.gtb.basicquiz.util.ConvertTool;
import org.springframework.stereotype.Service;

import javax.naming.CommunicationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EducationService {

    private final EducationRepository educationRepository;

    private final UserRepository userRepository;

    public EducationService(EducationRepository educationRepository,
                            UserRepository userRepository) {
        this.educationRepository = educationRepository;
        this.userRepository = userRepository;
    }

    public List<EducationDTO> getEducationByUserId(Long userId) throws CommunicationException {
        if (userRepository.existsById(userId)) {
            List<Education> educations = educationRepository.findAllByUserId(userId);
            return educations.stream()
                    .map(e -> ConvertTool.convert(e, EducationDTO.class))
                    .collect(Collectors.toList());
        }
        throw new CommunicationException("Education is not exist");
    }

    public EducationDTO saveEducation(Long userid, EducationDTO educationDto) throws CommunicationException {
        Optional<User> user = userRepository.findById(userid);
        if (user.isPresent()) {
            Education education = ConvertTool
                    .convert(educationDto, Education.class);
            education.setUser(user.get());
            Education savedEducation = educationRepository.save(education);
            return ConvertTool.convert(savedEducation, EducationDTO.class);
        }
        throw new CommunicationException("user is not exist");
    }
}
