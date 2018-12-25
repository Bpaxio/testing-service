package ru.otus.bbpax.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.bbpax.service.ActionRunner;

import java.io.InputStream;
import java.io.OutputStream;

@Configuration
public class AppConfiguration {

    @Bean
    public InputStream inputStream() {
        return System.in;
    }

    @Bean
    public OutputStream outputStream() {
        return System.out;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms
            = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:msg");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            ActionRunner exam = ctx.getBean(ActionRunner.class);
            exam.setUp();
            exam.run();
        };
    }
}
