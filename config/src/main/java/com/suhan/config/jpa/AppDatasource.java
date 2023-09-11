package com.suhan.config.jpa;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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
@EnableJpaRepositories(
        basePackages = "com.suhan.platform.repository",
        entityManagerFactoryRef = "soraManagerFactory",
        transactionManagerRef = "soraTransactionManager")
public class AppDatasource {

    @Autowired
    private Environment env;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    // 获取对应的数据库方言
    @Value("${spring.jpa.properties.hibernate.mysql-dialect}")
    private String mysqlDialect;

    @Primary
    @Bean(name = "soraData")
    @ConfigurationProperties(prefix = "sora.datasource")
    public DataSource customDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("sora.datasource.driver-class-name")));
        dataSource.setUrl(env.getProperty("sora.datasource.url"));
        dataSource.setUsername(env.getProperty("sora.datasource.username"));
        dataSource.setPassword(env.getProperty("sora.datasource.password"));
        return dataSource;
    }

    @Primary
    @Bean(name = "soraEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean transactionManagerFactory(EntityManagerFactoryBuilder builder,
                                                                            @Qualifier("soraData") DataSource dataSource){
        Map<String, String> map = new HashMap<>();
        // 设置对应的数据库方言
        map.put("hibernate.dialect", mysqlDialect);
        map.put("hibernate.show_sql", "true");
        jpaProperties.setProperties(map);
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder
                //设置数据源
                .dataSource(dataSource)
                //设置数据源属性
                .properties(properties)
                //设置实体类所在位置.扫描所有带有 @Entity 注解的类
                .packages("com.suhan.entity")
                // Spring会将EntityManagerFactory注入到Repository之中.有了 EntityManagerFactory之后,
                // Repository就能用它来创建 EntityManager 了,然后 EntityManager 就可以针对数据库执行操作
                .persistenceUnit("mysqlPersistenceUnit")
                .build();
    }

    /**
     * 配置事务管理器
     */
    @Bean(name = "soraTransactionManager")
    @Primary
    public PlatformTransactionManager managerFactory(
            @Qualifier("soraEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
