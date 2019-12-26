package jmx.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;

import java.io.IOException;

/**
 * @author binarylei
 * @version 2019-12-25
 */
@Configuration
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class SpringDemo {

    public static void main(String[] args) throws IOException {
        new AnnotationConfigApplicationContext(SpringDemo.class);

        System.in.read();
    }

    @Bean
    public User user(){
        return new User();
    }
}
