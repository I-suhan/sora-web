package com.suhan.core.provider;

import com.suhan.core.controller.Query;
import com.suhan.core.service.DemoService;
import graphql.GraphQL;
import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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
    }
}
