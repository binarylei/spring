package org.springframework.beans.factory.circle;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Main {

    public static void main(String[] args) {
        XmlBeanFactory beanFactory = new XmlBeanFactory(
                new ClassPathResource("spring-context-circle.xml"));
        beanFactory.addBeanPostProcessor(new CircleBeanPostProcessor());
        beanFactory.setAllowRawInjectionDespiteWrapping(true);

        BeanA beanA = (BeanA) beanFactory.getBean("beanA");
        beanA.say();
    }
}
