package com.thoughtworks.capability.gtb.basicquiz.repository;

import com.thoughtworks.capability.gtb.basicquiz.domain.Education;
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
    private long idInit=1;
    private List<Education> educationInit = new ArrayList<Education>(){
        {
            add(new Education(1, 1990, "I was born in Katowice",
                    "Lorem ipsum dolor sit amet, consectetur adipisicing elit. " +
                            "Sapiente, exercitationem, totam, dolores iste dolore est aut modi."));
            add(new Education(1, 2005, "Secondary school specializing in artistic",
                    "Eos, explicabo, nam, tenetur et ab eius deserunt aspernatur " +
                            "ipsum ducimus quibusdam quis voluptatibus."));
            add(new Education(1, 2009, "First level graduation in Graphic Design",
                    "Aspernatur, mollitia, quos maxime eius suscipit sed beatae ducimus " +
                            "quaerat quibusdam perferendis? Iusto, quibusdam asperiores unde repellat."));
            add(new Education(1, 2012, "Second level graduation in Graphic Design",
                    "Ducimus, aliquam tempore autem itaque et accusantium!"));
        }
    };
    private final Map<Long,List<Education>> educations = new HashMap<Long, List<Education>>(){
        {
            put(idInit,educationInit);
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
    @Override
    public List<Education> getEducationsByUserId(long id){
        return educations.get(id);
    };
}
