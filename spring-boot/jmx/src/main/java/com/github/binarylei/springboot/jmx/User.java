package com.github.binarylei.springboot.jmx;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedNotification;
import org.springframework.jmx.export.annotation.ManagedNotifications;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Component;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import java.util.concurrent.atomic.AtomicInteger;

@ManagedResource(objectName = "com.github.binarylei:type=User",
        description = "这里是描述，spring boot jmx")
@ManagedNotifications({
        @ManagedNotification(name = "User", description = "name 字段修改",
                notificationTypes = "jmx.attribute.change")})
@Component
public class User implements NotificationPublisherAware {

    private NotificationPublisher notificationPublisher;
    private AtomicInteger sequenceNumber = new AtomicInteger();

    private String name;

    // 属性可读
    @ManagedAttribute(description = "name 属性")
    public String getName() {
        return name;
    }

    // 属性可写
    @ManagedAttribute(description = "name 属性")
    public void setName(String name) {
        this.name = name;
        //发送通知
        Notification notification = new AttributeChangeNotification(this,
                sequenceNumber.incrementAndGet(), System.currentTimeMillis(),
                "name changed", "name", "String",
                this.name, name);
        notificationPublisher.sendNotification(notification);
    }

    @ManagedOperation(description = "打印操作")
    public void printUserInfo() {
        System.out.println(this.name);
    }

    @ManagedOperation(description = "Add two numbers")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "x", description = "The first number"),
            @ManagedOperationParameter(name = "y", description = "The second number")})
    public int add_1(int x, int y) {
        return x + y;
    }

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    }
}
