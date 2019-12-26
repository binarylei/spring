package jmx.sdk.mbean;

import org.junit.Test;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.management.PlatformManagedObject;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

public class UserAgentMain {

    @Test
    public void test() throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        ObjectName name = new ObjectName("UserAgent:type=User1");
        User user = new User();
        user.setName("test");
        user.setId(1);
        user.setBirthDate(new Date());
        user.setTime(LocalTime.now());
        TestBean test = new TestBean();
        test.setName("mytest");
        test.setAge(11);
        user.setTest(test);
        mBeanServer.registerMBean(user, name);

        Set<Class<? extends PlatformManagedObject>> interfaces =
                ManagementFactory.getPlatformManagementInterfaces();
        for (Class<? extends PlatformManagedObject> anInterface : interfaces) {
            System.out.println(anInterface.getName());
        }

        Thread.sleep(Long.MAX_VALUE);
    }

}
