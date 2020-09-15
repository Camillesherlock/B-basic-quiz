package com.thoughtworks.capability.gtb.basicquiz.service;

import com.thoughtworks.capability.gtb.basicquiz.domain.Education;
import com.thoughtworks.capability.gtb.basicquiz.domain.User;
import com.thoughtworks.capability.gtb.basicquiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeService {
    private final UserRepository userRepository;
    public ResumeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) {
        User byId = userRepository.findById(id);
        return byId;
    }

    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    public List<Education> getEducations(long userId) {
        List<Education> educations = userRepository.getEducationsByUserId(userId);
            return educations;
    }
}
