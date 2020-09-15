package com.thoughtworks.capability.gtb.basicquiz.repository;

import com.thoughtworks.capability.gtb.basicquiz.domain.Education;
import com.thoughtworks.capability.gtb.basicquiz.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    User findById(Long id);
    User addUser(User user);

    List<Education> getEducationsByUserId(long userId);

}
