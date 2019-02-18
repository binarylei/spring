package org.springframework.core;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

/**
 * @author: leigang
 * @version: 2019-01-26
 */
public class JdkParameterTest {

    @Test
    public void testGetParameter() throws NoSuchMethodException {
        Constructor<InnerClass> constructor = InnerClass.class.getConstructor(String.class);
        Parameter[] parameters1 = constructor.getParameters();

        Method method = InnerClass.class.getMethod("method", String.class);
        Parameter[] parameters2 = method.getParameters();
    }

    @Test
    public void testGetParameterName() throws Exception {
        Method method = InnerClass.class.getMethod("method", List.class);
        Parameter parameter = method.getParameters()[0];

        Assert.assertTrue(parameter.isNamePresent());
        Assert.assertEquals("username", parameter.getName());
        Assert.assertEquals("final", Modifier.toString(parameter.getModifiers()));
    }

    @Test
    public void testParameterType() throws Exception {
        Method method = InnerClass.class.getMethod("method", List.class);
        Parameter parameter = method.getParameters()[0];

        // 参数类型和泛型
        Assert.assertEquals(List.class, parameter.getType());
        Assert.assertEquals("java.util.List<java.util.Map<java.lang.String, java.util.List<java.lang.Integer>>>",
                parameter.getParameterizedType().getTypeName());
    }

    @Test
    public void testParameterAnnotation() throws Exception {
        Method method = InnerClass.class.getMethod("method", List.class);
        Parameter parameter = method.getParameters()[0];

        Assert.assertEquals("username", parameter.getAnnotation(PathVariable.class).value());
        Assert.assertEquals(2, parameter.getAnnotations().length);
    }

    public class InnerClass {
        public InnerClass(@Autowired String username) {
        }

        public void method(@PathVariable("username") @RequestBody final List<Map<String, List<Integer>>> users) {
        }
    }

    public static void main(String[] args) throws Exception {
        Method method = InnerClass.class.getMethod("method", String.class);
        for (final Parameter parameter : method.getParameters()) {
            System.out.println("isNamePresent: " + parameter.isNamePresent());
            System.out.println("Parameter: " + parameter.getName());
        }
    }
}
