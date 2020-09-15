package com.thoughtworks.capability.gtb.basicquiz.service;

import com.thoughtworks.capability.gtb.basicquiz.domain.Education;
import com.thoughtworks.capability.gtb.basicquiz.domain.User;
import com.thoughtworks.capability.gtb.basicquiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.naming.CommunicationException;
import java.util.List;

@Service
public class ResumeService {
    private final UserRepository userRepository;

    public ResumeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) throws CommunicationException {
        if(userRepository.findById(id) !=null){
        return userRepository.findById(id);}
        else {
            throw new CommunicationException("user is not exist");
        }
    }

    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    public List<Education> getEducations(long userId) throws CommunicationException {
        List<Education> educations = userRepository.getEducationsByUserId(userId);
        if(educations != null){
        return educations;}
        else {
            throw new CommunicationException("education is not exist");
        }
    }

    public Education addEducation(long userId, Education education) {
        education.setUserId(userId);
        return userRepository.addEducation(education);
    }
}
