package org.springframework.core.convert;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

import java.util.Map;

/**
 * @author: leigang
 * @version: 2019-01-13
 */
public class EnvironmentTest {

    @Test
    public void test() {
        Environment env = new StandardEnvironment();
        // 1. 操作系统的环境变量
        Map<String, Object> systemEnvironment = ((StandardEnvironment) env).getSystemEnvironment();
        Assert.assertNotNull(systemEnvironment);

        // 2. JVM 属性配置
        Map<String, Object> systemProperties = ((StandardEnvironment) env).getSystemProperties();
        Assert.assertNotNull(systemProperties);

        // 3. 属性
        Assert.assertEquals("UTF-8", env.getProperty("file.encoding"));
        Assert.assertTrue(env.containsProperty("file.encoding"));

        // 4. 剖面 spring.profiles.default(默认为 default) spring.profiles.active
        //    只要有一个返回 true acceptsProfiles 方法就返回 true，!a 为不包含该 profiles
        Assert.assertTrue(env.acceptsProfiles("default"));
        Assert.assertTrue(env.acceptsProfiles("a", "default"));
        Assert.assertFalse(env.acceptsProfiles("a"));
        Assert.assertTrue(env.acceptsProfiles("!a", "b"));


    }
}
