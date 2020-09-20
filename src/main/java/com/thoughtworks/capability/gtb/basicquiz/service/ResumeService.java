package com.thoughtworks.capability.gtb.basicquiz.service;

import com.thoughtworks.capability.gtb.basicquiz.domain.Education;
import com.thoughtworks.capability.gtb.basicquiz.domain.User;
import com.thoughtworks.capability.gtb.basicquiz.exception.CommonException;
import com.thoughtworks.capability.gtb.basicquiz.repository.EducationRepository;
import com.thoughtworks.capability.gtb.basicquiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.naming.CommunicationException;
import java.util.List;
import java.util.Optional;

@Service
public class ResumeService {
    private  final UserRepository userRepository;
    private  final EducationRepository educationRepository;

    public ResumeService(UserRepository userRepository, EducationRepository educationRepository) {
        this.userRepository = userRepository;
        this.educationRepository = educationRepository;
    }
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new CommonException("user_id not exists");
        }
    }

    public User addUser(User user) {
        userRepository.save(user);
        return userRepository.findById(user.getId()).get();
    }

    public List<Education> getEducations(long userId) throws CommunicationException {
        return educationRepository.findEducationsByUserId(userId);
    }

    public Education addEducation(long userId, Education education) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            education.setUser(user.get());
            educationRepository.save(education);
            return education;
        }
        throw new CommonException("user_id not exists");
    }
}
