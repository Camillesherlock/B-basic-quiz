package com.thoughtworks.capability.gtb.basicquiz.repository;

import com.thoughtworks.capability.gtb.basicquiz.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImp implements UserRepository {
    private User user = new User(1, "KAMIL", 24,
            "https://inews.gtimg.com/newsapp_match/0/3581582328/0",
            "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellendus, non, " +
                    "dolorem, cumque distinctio magni quam expedita velit laborum sunt amet facere tempora ut " +
                    "fuga aliquam ad asperiores voluptatem dolorum! Quasi.");
    private final Map<Long,User> users = new HashMap<Long, User>(){
        {
            put(user.getId(), user);
        }
    };

    @Override
    public User findById(Long id) {
        return users.get(id);
    }
    @Override
    public User addUser(User user) {
        user.setId(users.size()+1);
        users.put(user.getId(),user);
        return users.get(users.size()-1);
    }
}
