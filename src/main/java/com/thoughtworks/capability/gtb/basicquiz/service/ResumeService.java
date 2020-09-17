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
        // GTB: - 基本的格式化没做好，让 IDEA 自动格式化一下
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
        // GTB: - 基本的格式化没做好，让 IDEA 自动格式化一下
        List<Education> educations = userRepository.getEducationsByUserId(userId);
        if(educations != null){
        return educations;}
        else {
            // GTB: - 如果某个 use 就是单纯的没有 educations，也要抛异常吗？
            throw new CommunicationException("education is not exist");
        }
    }

    public Education addEducation(long userId, Education education) {
        // GTB: - userId 不存在也会保存吗？
        education.setUserId(userId);
        return userRepository.addEducation(education);
    }
}
