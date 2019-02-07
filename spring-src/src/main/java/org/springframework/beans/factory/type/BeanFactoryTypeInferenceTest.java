package org.springframework.beans.factory.type;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryTypeInferenceTest {

    @Test
    public void test() {
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(lbf);
        reader.loadBeanDefinitions(new ClassPathResource("spring-context-factory.xml", getClass()));

        // 1. 直接定义 className
        Assert.assertTrue(lbf.isTypeMatch("bean1", User.class));
        // 2. FactoryBean
        Assert.assertTrue(lbf.isTypeMatch("bean2", User.class));
        // 3. 实例工厂
        Assert.assertTrue(lbf.isTypeMatch("bean3", User.class));
        // 4. 静态工厂
        Assert.assertTrue(lbf.isTypeMatch("bean4", User.class));
        // 5.1 工厂方法没有定义泛型
        Assert.assertTrue(lbf.isTypeMatch("bean5.1", User.class));
        // 5.2 工厂方法定义泛型，但返回值类型与泛型无关
        Assert.assertTrue(lbf.isTypeMatch("bean5.2", User.class));
        // 5.3 工厂方法定义泛型，返回值类型与参数类型决定，无法获取 @Spring 5.1.3
        Assert.assertFalse(lbf.isTypeMatch("bean5.3", User.class));
        // 6.1 静态工厂方法返回类型为 FactoryBean
        Assert.assertTrue(lbf.isTypeMatch("bean6.1", User.class));
        // 6.2 实例工厂方法返回类型为 FactoryBean
        Assert.assertFalse(lbf.isTypeMatch("bean6.2", User.class));
    }

    public static class User {
    }

    public static class UserFactoryBean implements FactoryBean<User> {
        @Override
        public User getObject() throws Exception {
            return new User();
        }

        @Override
        public Class<?> getObjectType() {
            return User.class;
        }
    }

    public static class UserFactory {
        // 1. 实例工厂
        public User getObject1() {
            return new User();
        }

        // 2. 静态工厂
        public static User getObject2() {
            return new User();
        }

        // 3.1 带参的工厂方法
        public User getObject3(String username, String password) {
            return new User();
        }

        // 3.2 带泛型的工厂方法，但返回值类型和泛型无关
        public <T, K, V> User getObject4(T t, String username) {
            return new User();
        }

        // 3.3 带泛型的工厂方法，但返回值类型由参数决定
        public <T, K, V> T getObject5(T t) {
            return t;
        }

        // 4.1 静态工厂方法返回类型为 FactoryBean
        public static FactoryBean<User> getObject6() {
            return new UserFactoryBean();
        }

        // 4.2 实例工厂方法返回类型为 FactoryBean
        public FactoryBean<User> getObject7() {
            return new UserFactoryBean();
        }
    }
}
