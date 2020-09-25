package me.ryubato.sample;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = {"default", "local", "test"})
public class ApplicationPropertyOverrideTest {

    private final Logger log = LoggerFactory.getLogger(ApplicationPropertyOverrideTest.class);

    @Value("${spring.datasource.driver-class-name}")
    private String driver_class_name;

    @Value("${spring.thymeleaf.mode}")
    private String thymeleafMode;

    @Test
    void init() {
        log.info("driver_class_name : " + driver_class_name);
        log.info("username : " + thymeleafMode);
    }

    /**
     * @SpringBootTest, @ActiveProfiles는 "spring.profiles: default" 속성 값들을 무시한다.
     * profile
     */
}
