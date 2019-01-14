package ru.otus.bbpax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Vlad Rakhlinskii
 * Created on 06.12.2018.
 */
@SpringBootApplication
@EnableConfigurationProperties
public class TestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestServiceApplication.class);
    }
}
