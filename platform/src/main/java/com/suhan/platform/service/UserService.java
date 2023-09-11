package com.suhan.platform.service;

import com.suhan.platform.entity.Gender;
import com.suhan.platform.entity.User;
import com.suhan.platform.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getStaticUser(){
        User user = new User();
        user.setId("aaaa111");
        user.setName("mike");
        user.setAge(18);
        user.setGender(Gender.MALE);
        User friend1 = new User();
        friend1.setId("bbbb222");
        friend1.setName("jack");
        friend1.setAge(13);
        friend1.setGender(Gender.MALE);
        User friend2 = new User();
        friend2.setId("cccc333");
        friend2.setName("clark");
        friend2.setAge(9);
        friend2.setGender(Gender.FEMALE);
        User friend3 = new User();
        friend3.setId("dddd444");
        friend3.setName("ane");
        friend3.setAge(22);
        friend3.setGender(Gender.MALE);
        List<User> list = new ArrayList<>();
        list.add(friend1);
        list.add(friend2);
        list.add(friend3);
        user.setFriends(list);
        return user;
    }

    public User getRepositoryUser(String userId){
        return userRepository.getReferenceById(userId);
    }

    @Transactional(readOnly = true)
    public List<User> getUsers(int size) {
        return userRepository.findAll().stream().limit(size).collect(Collectors.toList());
    }
}
