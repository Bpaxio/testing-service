package ru.otus.bbpax.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.io.InputStream;
import java.io.OutputStream;

@Configuration
@PropertySource({"classpath:config.properties"})
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
        ms.setBasename("/msg");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
