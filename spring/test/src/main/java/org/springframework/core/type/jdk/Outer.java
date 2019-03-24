package org.springframework.core.type.jdk;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Outer {
    public Outer() {
        /**
         * 构造方法中的匿名内部类
         * enclosingClass = class Outer
         * enclosingConstructor = public Outer()
         * enclosingMethod = null
         */
        InnerClass innerClass = new InnerClass() {
            @Override
            public void fun() {
                getEnclosing(this.getClass());
            }
        };
        innerClass.fun();
    }

    /**
     * 匿名内部类
     * enclosingClass = class Outer
     * enclosingConstructor = null
     * enclosingMethod = null
     */
    static InnerClass innerClass = new InnerClass() {
        public void fun() {
            getEnclosing(this.getClass());
        }
    };

    /**
     * 方法中的匿名内部类
     * enclosingClass = class Outer
     * enclosingConstructor = null
     * enclosingMethod = public static test()
     */
    public static void test() {
        InnerClass innerClass = new InnerClass() {
            @Override
            public void fun() {
                getEnclosing(this.getClass());
            }
        };
        innerClass.fun();
    }

    // 内部类
    public static class InnerClass {
        public InnerClass() {
        }

        public void fun() {
        }

    }

    public static void main(String[] args) {
        System.out.println("------顶级类------");
        getEnclosing(Outer.class);

        System.out.println("------内部类------");
        getEnclosing(InnerClass.class);

        System.out.println("------匿名内部类------");
        innerClass.fun();

        System.out.println("------方法中的匿名内部类------");
        Outer.test();

        System.out.println("------构造函数中的匿名内部类------");
        new Outer();
    }

    /**
     * getEnclosingClass: 该类是在那个类中定义的， 比如直接定义的内部类或匿名内部类
     * getEnclosingConstructor: 该类是在哪个构造函数中定义的，比如构造方法中定义的匿名内部类
     * getEnclosingMethod: 该类是在哪个方法中定义的，比如方法中定义的匿名内部类
     *
     * @param clazz
     */
    private static void getEnclosing(Class clazz) {
        Class enclosingClass = clazz.getEnclosingClass();
        Constructor enclosingConstructor = clazz.getEnclosingConstructor();
        Method enclosingMethod = clazz.getEnclosingMethod();

        System.out.println(String.format("------enclosingClass: %s------", clazz.getName()));
        System.out.println("enclosingClass = " + enclosingClass);
        System.out.println("enclosingConstructor = " + enclosingConstructor);
        System.out.println("enclosingMethod = " + enclosingMethod);
        System.out.println("declaringClass = " + clazz.getDeclaringClass());
        System.out.println();
    }

}
