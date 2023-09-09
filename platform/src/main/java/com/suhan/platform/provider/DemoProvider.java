package com.suhan.platform.provider;

import com.suhan.platform.service.DemoService;
import graphql.GraphQL;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DemoProvider {

    private GraphQL graphQL;

    private final DemoService demoService;

    public DemoProvider(DemoService demoService) {
        this.demoService = demoService;
    }

    @Bean
    public GraphQL graphQL(){
        return graphQL;
    }

    @PostConstruct
    public void init(){
        //todo
    }
}
