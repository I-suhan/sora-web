package com.suhan.config;/*
package com.suhan.configuration.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.suhan.xxx.repository", entityManagerFactoryRef = "otherManagerFactoryBean")
public class OtherDatasource {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.other")
    public DataSourceProperties otherDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.other.configuration")
    public HikariDataSource otherDataSource() {
        return otherDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "otherEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean otherManagerFactoryBean(EntityManagerFactoryBuilder builder){
        return builder.dataSource(otherDataSource())
                .packages("com.suhan.core.entity").build();
    }

    @Bean(name = "otherTransactionManager")
    @Primary
    public PlatformTransactionManager otherTransactionManager(
            @Qualifier("otherEntityManagerFactory") final LocalContainerEntityManagerFactoryBean entityManagerFactory){
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}
*/
