package org.springframework.beans.factory.support.qualifier;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QualifierTest2 {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-context-qualifier2.xml", this.getClass());
        TestBean bean = context.getBean(TestBean.class);
        Assert.assertEquals("OracleDriveManagerDataSource", bean.getDataSource().getClass().getSimpleName());
    }

    public static class TestBean {
        @Autowired
        @Qualifier("oracleDataSource")
        private DataSource dataSource;

        public DataSource getDataSource() {
            return dataSource;
        }

        public void setDataSource(DataSource dataSource1) {
            this.dataSource = dataSource;
        }
    }

}
