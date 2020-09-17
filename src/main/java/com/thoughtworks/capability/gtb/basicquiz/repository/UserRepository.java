package com.thoughtworks.capability.gtb.basicquiz.repository;

import com.thoughtworks.capability.gtb.basicquiz.domain.Education;
import com.thoughtworks.capability.gtb.basicquiz.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

// GTB: + 加了单独的 Repository 接口，后面可以进一步把接口定义和实现放到不同的包里去
@Repository
public interface UserRepository {
    User findById(Long id);
    User addUser(User user);

    List<Education> getEducationsByUserId(long userId);

    Education addEducation(Education education);
}
