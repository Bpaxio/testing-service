package ru.otus.bbpax;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Vlad Rakhlinskii
 * Created on 06.12.2018.
 */
public class SpringApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
    }
}
