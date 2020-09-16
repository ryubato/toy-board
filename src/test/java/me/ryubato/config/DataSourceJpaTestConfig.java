package me.ryubato.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
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
 */
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@PropertySources(value = {
        @PropertySource(""),
        @PropertySource("")})
public class DataSourceJpaTestConfig {

    private static final Logger log = LoggerFactory.getLogger(DataSourceJpaTestConfig.class);

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("datasource") DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("");
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(jpaProperties());
        return em;
    }

    private Properties jpaProperties() {
        return null;
    }

    @Bean
    public DataSource dataSource() {
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        builder.setType(EmbeddedDatabaseType.H2)
//                .build();

        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName();
//        dataSource.setJdbcUrl();
//        dataSource.setUsername();
//        dataSource.setPassword();
        return dataSource;
    }
}
