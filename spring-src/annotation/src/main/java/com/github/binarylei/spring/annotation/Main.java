package com.github.binarylei.spring.annotation;

import com.github.binarylei.spring.annotation.bean.User;
import com.github.binarylei.spring.annotation.config.AnnnotationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnnotationConfig.class);
        // AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // context.register(AnnnotationConfig.class);
        // context.refresh();

        User user = context.getBean(User.class);
    }
}
