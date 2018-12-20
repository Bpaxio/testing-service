package ru.otus.bbpax;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.bbpax.service.ActionRunner;

/**
 * @author Vlad Rakhlinskii
 * Created on 06.12.2018.
 */
@Configuration
@ComponentScan
public class SpringApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.register(SpringApplication.class);
        context.refresh();

        ActionRunner exam = context.getBean(ActionRunner.class);
        exam.setUp();
        exam.run();
    }
}
