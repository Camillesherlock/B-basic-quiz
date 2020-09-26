package com.thoughtworks.capability.gtb.basicquiz.service;

import com.thoughtworks.capability.gtb.basicquiz.dto.UserDTO;
import com.thoughtworks.capability.gtb.basicquiz.entity.User;
import com.thoughtworks.capability.gtb.basicquiz.repository.UserRepository;
import com.thoughtworks.capability.gtb.basicquiz.util.ConvertTool;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).get();
        return ConvertTool.convert(user, UserDTO.class);
    }

    public UserDTO saveUser(UserDTO userDto) {
        User userEntity = ConvertTool.convert(userDto, User.class);
        User savedUserEntity = userRepository.save(userEntity);
        return ConvertTool.convert(savedUserEntity, UserDTO.class);
    }
}
