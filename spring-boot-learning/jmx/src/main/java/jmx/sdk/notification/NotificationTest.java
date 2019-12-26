package jmx.sdk.notification;

import org.junit.Test;

import javax.management.MBeanServer;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class NotificationTest {

    @Test
    public void test1() throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        ObjectName name = new ObjectName("NotificationAgentMain:type=User");
        mBeanServer.registerMBean(new User(), name);

        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void test2() throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        ObjectName name = new ObjectName("NotificationAgentMain:type=User");
        mBeanServer.registerMBean(new User(), name);
        mBeanServer.addNotificationListener(name,
                new NotificationListener() {
                    @Override
                    public void handleNotification(Notification notification, Object handback) {
                        System.out.println(notification.getSource());
                    }
                }, null, null);

        Thread.sleep(Long.MAX_VALUE);
    }

}
