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
@EnableJpaRepositories(basePackages = "com.suhan.core.repository", entityManagerFactoryRef = "soraManagerFactoryBean")
@ConfigurationProperties(prefix = "spring.datasource")
public class SoraDatasource {

    @Bean
    @Primary
    @ConfigurationProperties("sora")
    public DataSourceProperties soraDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("sora.configuration")
    public HikariDataSource soraDataSource() {
        return soraDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "soraEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean soraManagerFactoryBean(EntityManagerFactoryBuilder builder){
        return builder.dataSource(soraDataSource())
                .packages("com.suhan.core.entity").build();
    }

    @Bean(name = "soraTransactionManager")
    @Primary
    public PlatformTransactionManager soraTransactionManager(
            @Qualifier("soraEntityManagerFactory") final LocalContainerEntityManagerFactoryBean entityManagerFactory){
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}
