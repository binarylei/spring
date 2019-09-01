package com.github.binarylei.jdk.jmx.mxbean;

import com.github.binarylei.jdk.jmx.mbean.TestBean;
import org.junit.Test;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class HelloAgent {

    @Test
    public void test() throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("HelloAgent:type=Hello");

        TestBean test = new TestBean();
        test.setName("mytest");
        test.setAge(11);
        HelloImpl hello = new HelloImpl();
        hello.setTest(test);
        hello.setName("hello world");
        server.registerMBean(hello, name);

        Thread.sleep(Long.MAX_VALUE);
    }
}
