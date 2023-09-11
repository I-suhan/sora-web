package com.suhan.platform.controller;

import com.suhan.platform.entity.User;
import com.suhan.platform.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.graphql.data.GraphQlRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@GraphQlRepository
public class UserResolver implements GraphQLQueryResolver {

    private final UserService userService;

    public UserResolver(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public User getStaticUser() {
        return userService.getStaticUser();
    }

    @QueryMapping
    public User getOneUser(@Argument String userId){
        return userService.getRepositoryUser(userId);
    }

    @QueryMapping
    public List<User> getUsers(@Argument int size){
        return userService.getUsers(size);
    }
}
