package com.github.binarylei.jdk.jmx.notification;

import org.junit.Test;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class NotificationAgentMain {

    @Test
    public void test() throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        ObjectName name = new ObjectName("NotificationAgentMain:type=User");
        mBeanServer.registerMBean(new User(), name);

        Thread.sleep(Long.MAX_VALUE);
    }

}
