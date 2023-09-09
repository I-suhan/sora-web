package com.suhan.core.controller;

import com.suhan.core.service.DemoService;
import com.suhan.core.entity.User;
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
