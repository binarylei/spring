package org.springframework.beans.factory.di;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

public class BeanFactoryDITest {

    @Test
    public void test() {
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(lbf);
        reader.loadBeanDefinitions(new ClassPathResource("spring-context-di.xml", getClass()));

        // 1. 名称注入
        Company companyByName = (Company) lbf.getBean("company1");
        // 2. 类型注入，支持 List 方式注入，如果本地容器找到多个则直接抛出异常
        Company companyByType = (Company) lbf.getBean("company2");
        // 3. no
        Company companyByNo = (Company) lbf.getBean("company3");
        // 4. 构造器注入
        Company companyByConstructor = (Company) lbf.getBean("company4");
        // 5. 默认
        Company companyDefault = (Company) lbf.getBean("company5");
    }

    public static class Company {
        private Department department;
        private List<Employee> employees;

        public Company() {
        }

        public Company(Department department) {
            this.department = department;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }
        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }

    public static class Employee {
        private String name;
        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Department {
        private String name;
        public void setName(String name) {
            this.name = name;
        }
    }

}
