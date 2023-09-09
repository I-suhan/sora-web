package com.suhan.platform.controller;

import com.suhan.platform.entity.User;
import com.suhan.platform.service.DemoService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    private final DemoService demoService;

    public Query(DemoService demoService) {
        this.demoService = demoService;
    }

    public User getOneUser() {
        return demoService.getUser();
    }
}
