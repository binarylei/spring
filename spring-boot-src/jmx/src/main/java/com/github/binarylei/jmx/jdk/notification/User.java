package com.github.binarylei.jmx.jdk.notification;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import java.util.concurrent.atomic.AtomicInteger;

public class User extends NotificationBroadcasterSupport implements UserMBean,
        NotificationListener, NotificationFilter {
    private AtomicInteger sequenceNumber = new AtomicInteger();
    private String name;

    public User() {
        addNotificationListener(this, this, null);
    }

    @Override
    public void setName(String name) {
        this.name = name;
        //发送通知
        Notification notification = new AttributeChangeNotification(this,
                sequenceNumber.incrementAndGet(), System.currentTimeMillis(),
                "name changed", "name", "String",
                this.name, name);
        sendNotification(notification);
    }

    @Override
    public boolean isNotificationEnabled(Notification notification) {
        if (notification instanceof AttributeChangeNotification) {
            AttributeChangeNotification attrNotification = (AttributeChangeNotification) notification;
            if ("name".equals(attrNotification.getAttributeName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {
        printUserInfo();
    }

    /**
     * 暴露通知信息
     *
     * @return
     */
    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{AttributeChangeNotification.ATTRIBUTE_CHANGE};
        String name = AttributeChangeNotification.class.getName();
        String description = "An attribute of this MBean has changed";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name,
                description);
        return new MBeanNotificationInfo[]{info};
    }

    @Override
    public void printUserInfo() {
        System.out.println("name 的值修改为：" + this.name);
    }

    @Override
    public String getName() {
        return name;
    }

}
