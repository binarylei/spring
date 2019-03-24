package org.springframework.core.annotation.jdk;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Controller
public class InheritedTest {

    @Test
    public void testClassInherited() {
        // 类上注解可以继承(@Inherited)
        Assert.assertNull(MyInheritedClass.class.getAnnotation(NoInherritedAnnotation.class));
        Assert.assertNotNull(MyInheritedClass.class.getAnnotation(IsInheritedAnnotation.class));
    }

    @Test
    public void testInterfaceInherited() {
        // 接口上注解无法继承(@Inherited)
        Assert.assertNull(IInheritedInterfaceChild.class.getAnnotation(NoInherritedAnnotation.class));
        Assert.assertNull(IInheritedInterfaceChild.class.getAnnotation(IsInheritedAnnotation.class));

        Assert.assertNull(MyInheritedClassUseInterface.class.getAnnotation(NoInherritedAnnotation.class));
        Assert.assertNull(MyInheritedClassUseInterface.class.getAnnotation(IsInheritedAnnotation.class));
    }

    @Test
    public void testClassMethodInherited() throws Exception {
        // 方法重写后注解不能继承，无论是否有泛型(@Inherited)
        Assert.assertNull(MyInheritedClass.class.getMethod("test", Object.class)
                .getAnnotation(IsInheritedAnnotation.class));
        // 直接获取方法上的注解
        Assert.assertNotNull(MyInheritedClass.class.getMethod("test", null)
                .getAnnotation(IsInheritedAnnotation.class));
    }

    @Test
    public void test() {
        Assert.assertNotNull(AnnotationUtils.getAnnotation(InheritedTest.class, Component.class));
    }

    // 类继承
    @NoInherritedAnnotation
    @IsInheritedAnnotation
    public class InheritedBase<T> {
        @IsInheritedAnnotation
        public void test(T obj) {
        }

        @IsInheritedAnnotation
        public void test() {
        }
    }

    public class MyInheritedClass extends InheritedBase<String> {
        @Override
        public void test(String obj) {
        }
    }

    // 接口继承
    @NoInherritedAnnotation
    @IsInheritedAnnotation
    public interface IInheritedInterface {
    }

    public interface IInheritedInterfaceChild extends IInheritedInterface {
    }

    public class MyInheritedClassUseInterface implements IInheritedInterface {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Inherited
    public @interface IsInheritedAnnotation {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    public @interface NoInherritedAnnotation {
    }
}
