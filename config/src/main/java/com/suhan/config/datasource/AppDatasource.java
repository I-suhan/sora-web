package com.suhan.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration(proxyBeanMethods = false)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.suhan.platform.repository", entityManagerFactoryRef = "soraManagerFactoryBean")
public class AppDatasource {

    @Primary
    @Bean(name = "soraDataSource")
    @ConfigurationProperties(prefix = "app.datasource.sora")
    public DataSource soraDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "otherDataSource")
    @ConfigurationProperties(prefix = "app.datasource.other")
    public DataSource otherDataSource() {
        return DataSourceBuilder.create().build();
    }
}
