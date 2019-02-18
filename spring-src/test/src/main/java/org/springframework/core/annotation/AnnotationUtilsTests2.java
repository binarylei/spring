package org.springframework.core.annotation;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AnnotationUtilsTests2 {

    @Test
    @AliasFor(value = "test")
    public void testAliasFor() throws NoSuchMethodException {
        Method method = AnnotationUtilsTests2.class.getMethod("testAliasFor", null);

        AliasFor aliasFor1 = method.getAnnotation(AliasFor.class);
        AliasFor aliasFor2 = AnnotationUtils.synthesizeAnnotation(aliasFor1, null);
    }

    @Test
    public void testGetAttributeOverrideName() throws Exception {
        Method attribute = ContextConfiguration.class.getDeclaredMethod("value");
        assertEquals("name", AnnotationUtils.getAttributeOverrideName(attribute, ContextConfiguration.class));
    }

    @Test
    public void testAliasFor2() throws Exception {
        Map<String, List<String>> attributeAliasMap =
                AnnotationUtils.getAttributeAliasMap(AliasFor.class);
    }

    @Test
    public void testGetAttributeOverrideName2() throws Exception {
        assertEquals("locations", AnnotationUtils.getAttributeOverrideName(
                MyTestConfig.class.getDeclaredMethod("value"), ContextConfiguration.class));

        assertEquals("locations", AnnotationUtils.getAttributeOverrideName(
                GroovyOrXmlTestConfig.class.getDeclaredMethod("xml"), ContextConfiguration.class));

        assertEquals("locations", AnnotationUtils.getAttributeOverrideName(
                GroovyOrXmlTestConfig.class.getDeclaredMethod("groovy"), ContextConfiguration.class));
    }


    @Test
    public void test() throws NoSuchMethodException {
        boolean equals = GroovyOrXmlTestConfig.class.getDeclaredMethod("groovy").equals(
                GroovyOrXmlTestConfig.class.getDeclaredMethod("groovy"));
        System.out.println(equals);
    }

    @ContextConfiguration
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MyTestConfig {
        @AliasFor(annotation = ContextConfiguration.class, attribute = "locations")
        String[] value() default {};
    }

    @MyTestConfig
    public @interface GroovyOrXmlTestConfig {
        @AliasFor(annotation = MyTestConfig.class, attribute = "value")
        String[] groovy() default {};

        @AliasFor(annotation = ContextConfiguration.class, attribute = "locations")
        String[] xml() default {};
    }

}