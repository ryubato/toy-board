package me.ryubato.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;


/**
 * Repository Unit Test when Using Custom Datasource Property
 *
 * Self-Contained Testing 를 위한 Config 를 작성한다.
 * https://www.baeldung.com/spring-jpa-test-in-memory-database
 *
 * 문제 PropertySource 를 받아올때 yaml 파일인 경우 YamlPropertySourceFactory 이 필요하다.
 * https://www.baeldung.com/spring-yaml-propertysource
 * 
 * Table Not Found 문제
 * https://www.thomasvitale.com/spring-data-jpa-hibernate-java-configuration/
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = "me.ryubato.domain")
@EnableTransactionManagement

@PropertySources(value = {
//        @PropertySource(value = "classpath:/config/hibernate.properties"),  // null 인경우 error
        @PropertySource(value = "classpath:/application.yml", factory = YamlPropertySourceFactory.class)})
public class DataSourceJpaTestConfig {

    private static final Logger log = LoggerFactory.getLogger(DataSourceJpaTestConfig.class);

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("me.ryubato.domain");
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(Objects.requireNonNull(env.getProperty("spring.datasource.url")));
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    final Properties additionalProperties() {
        final Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", Objects.requireNonNull(env.getProperty("spring.jpa.hibernate.ddl-auto")));
        jpaProperties.setProperty("hibernate.dialect", Objects.requireNonNull(env.getProperty("spring.jpa.properties.hibernate.dialect")));
        return jpaProperties;
    }
}
