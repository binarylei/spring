package com.github.binarylei.springcloud.zuul;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * @author leigang
 * @version 2019-03-30
 */
@Configuration
public class Main {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        context.addApplicationListener(new MyApplicationListener());
        context.publishEvent(new MyApplicationEvent("myevent"));
    }

    static class MyApplicationEvent extends ApplicationEvent {
        public MyApplicationEvent(Object source) {
            super(source);
        }
    }
    static class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {
        @Override
        public void onApplicationEvent(MyApplicationEvent event) {
            System.out.println(event.getSource());
        }
    }

}
