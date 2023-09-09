package com.suhan.config.datasource;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.suhan.platform.repository"},
                        entityManagerFactoryRef = "soraEntityManagerFactory",
                        transactionManagerRef = "soraTransactionManager")
public class SoraDataSourceConfig {

    @Autowired
    @Qualifier("soraDataSource")
    private DataSource soraDataSource;


    @Primary
    @Bean(name = "soraEntityManager")
    public EntityManager soraEntityManager(EntityManagerFactoryBuilder builder) {
        return soraEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "soraEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean soraEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.hbm2ddl.auto", "update");
        map.put("hibernate.show_sql", "true");
        return builder.dataSource(soraDataSource).packages("com.example.multidssplitpackage.sora").properties(map).persistenceUnit("soraUnit").build();
    }

    @Primary
    @Bean(name = "soraTransactionManager")
    public PlatformTransactionManager soraTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(soraEntityManagerFactory(builder).getObject()));
    }

}

