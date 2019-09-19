package org.springframework.beans.factory;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.tests.sample.beans.ITestBean;
import org.springframework.tests.sample.beans.TestBean;
import org.springframework.tests.sample.beans.factory.DummyFactory;
import org.springframework.core.OverridingClassLoader;
import org.springframework.core.ResolvableType;

import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author: leigang
 * @version: 2019-01-22
 */
public class DefaultListableBeanFactoryTests {

    @Test
    public void testUnreferencedSingletonWasInstantiated() {
        KnowsIfInstantiated.clearInstantiationRecord();
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        Properties p = new Properties();
        p.setProperty("x1.(class)", KnowsIfInstantiated.class.getName());
        assertTrue("singleton not instantiated", !KnowsIfInstantiated.wasInstantiated());
        (new PropertiesBeanDefinitionReader(lbf)).registerBeanDefinitions(p);
        lbf.preInstantiateSingletons();
        assertTrue("singleton was instantiated", KnowsIfInstantiated.wasInstantiated());
    }

    @Test
    public void testLazyInitialization() {
        KnowsIfInstantiated.clearInstantiationRecord();
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        Properties p = new Properties();
        p.setProperty("x1.(class)", KnowsIfInstantiated.class.getName());
        p.setProperty("x1.(lazy-init)", "true");
        assertTrue("singleton not instantiated", !KnowsIfInstantiated.wasInstantiated());
        (new PropertiesBeanDefinitionReader(lbf)).registerBeanDefinitions(p);
        assertTrue("singleton not instantiated", !KnowsIfInstantiated.wasInstantiated());
        lbf.preInstantiateSingletons();

        assertTrue("singleton not instantiated", !KnowsIfInstantiated.wasInstantiated());
        lbf.getBean("x1");
        assertTrue("singleton was instantiated", KnowsIfInstantiated.wasInstantiated());
    }

    @Test
    public void testFactoryBeanDidNotCreatePrototype() {
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        Properties p = new Properties();
        p.setProperty("x1.(class)", DummyFactory.class.getName());
        // ResregisterBeanDefinitionset static state
        DummyFactory.reset();
        p.setProperty("x1.singleton", "false");
        assertTrue("prototype not instantiated", !DummyFactory.wasPrototypeCreated());
        (new PropertiesBeanDefinitionReader(lbf)).registerBeanDefinitions(p);
        assertTrue("prototype not instantiated", !DummyFactory.wasPrototypeCreated());
        assertEquals(TestBean.class, lbf.getType("x1"));
        lbf.preInstantiateSingletons();

        assertTrue("prototype not instantiated", !DummyFactory.wasPrototypeCreated());
        lbf.getBean("x1");
        assertEquals(TestBean.class, lbf.getType("x1"));
        assertTrue(lbf.containsBean("x1"));
        assertTrue(lbf.containsBean("&x1"));
        assertTrue("prototype was instantiated", DummyFactory.wasPrototypeCreated());
    }

    @Test
    public void testPrototypeFactoryBeanIgnoredByNonEagerTypeMatching() {
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        Properties p = new Properties();
        p.setProperty("x1.(class)", DummyFactory.class.getName());
        // Reset static state
        DummyFactory.reset();
        p.setProperty("x1.(singleton)", "false");
        p.setProperty("x1.singleton", "false");
        (new PropertiesBeanDefinitionReader(lbf)).registerBeanDefinitions(p);

        assertTrue("prototype not instantiated", !DummyFactory.wasPrototypeCreated());
        String[] beanNames = lbf.getBeanNamesForType(TestBean.class, true, false);
        assertEquals(0, beanNames.length);
        beanNames = lbf.getBeanNamesForAnnotation(SuppressWarnings.class);
        assertEquals(0, beanNames.length);

        assertFalse(lbf.containsSingleton("x1"));
        assertTrue(lbf.containsBean("x1"));
        assertTrue(lbf.containsBean("&x1"));
        assertFalse(lbf.isSingleton("x1"));
        assertFalse(lbf.isSingleton("&x1"));
        assertTrue(lbf.isPrototype("x1"));
        assertTrue(lbf.isPrototype("&x1"));
        assertTrue(lbf.isTypeMatch("x1", TestBean.class));
        assertFalse(lbf.isTypeMatch("&x1", TestBean.class));
        assertTrue(lbf.isTypeMatch("&x1", DummyFactory.class));
        assertTrue(lbf.isTypeMatch("&x1", ResolvableType.forClass(DummyFactory.class)));
        assertTrue(lbf.isTypeMatch("&x1", ResolvableType.forClassWithGenerics(FactoryBean.class, Object.class)));
        assertFalse(lbf.isTypeMatch("&x1", ResolvableType.forClassWithGenerics(FactoryBean.class, String.class)));
        assertEquals(TestBean.class, lbf.getType("x1"));
        assertEquals(DummyFactory.class, lbf.getType("&x1"));
        assertTrue("prototype not instantiated", !DummyFactory.wasPrototypeCreated());
    }

    @Test
    public void testSingletonFactoryBeanIgnoredByNonEagerTypeMatching() {
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        Properties p = new Properties();
        p.setProperty("x1.(class)", DummyFactory.class.getName());
        // Reset static state
        DummyFactory.reset();
        p.setProperty("x1.(singleton)", "false");
        p.setProperty("x1.singleton", "true");
        (new PropertiesBeanDefinitionReader(lbf)).registerBeanDefinitions(p);

        assertTrue("prototype not instantiated", !DummyFactory.wasPrototypeCreated());
        String[] beanNames = lbf.getBeanNamesForType(TestBean.class, true, false);
        assertEquals(0, beanNames.length);
        beanNames = lbf.getBeanNamesForAnnotation(SuppressWarnings.class);
        assertEquals(0, beanNames.length);

        assertFalse(lbf.containsSingleton("x1"));
        assertTrue(lbf.containsBean("x1"));
        assertTrue(lbf.containsBean("&x1"));
        assertFalse(lbf.isSingleton("x1"));
        assertFalse(lbf.isSingleton("&x1"));
        assertTrue(lbf.isPrototype("x1"));
        assertTrue(lbf.isPrototype("&x1"));
        assertTrue(lbf.isTypeMatch("x1", TestBean.class));
        assertFalse(lbf.isTypeMatch("&x1", TestBean.class));
        assertTrue(lbf.isTypeMatch("&x1", DummyFactory.class));
        assertTrue(lbf.isTypeMatch("&x1", ResolvableType.forClass(DummyFactory.class)));
        assertTrue(lbf.isTypeMatch("&x1", ResolvableType.forClassWithGenerics(FactoryBean.class, Object.class)));
        assertFalse(lbf.isTypeMatch("&x1", ResolvableType.forClassWithGenerics(FactoryBean.class, String.class)));
        assertEquals(TestBean.class, lbf.getType("x1"));
        assertEquals(DummyFactory.class, lbf.getType("&x1"));
        assertTrue("prototype not instantiated", !DummyFactory.wasPrototypeCreated());
    }

    @Test
    public void testNonInitializedFactoryBeanIgnoredByNonEagerTypeMatching() {
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        Properties p = new Properties();
        p.setProperty("x1.(class)", DummyFactory.class.getName());
        // Reset static state
        DummyFactory.reset();
        p.setProperty("x1.singleton", "false");
        (new PropertiesBeanDefinitionReader(lbf)).registerBeanDefinitions(p);

        assertTrue("prototype not instantiated", !DummyFactory.wasPrototypeCreated());
        String[] beanNames = lbf.getBeanNamesForType(TestBean.class, true, false);
        assertEquals(0, beanNames.length);
        beanNames = lbf.getBeanNamesForAnnotation(SuppressWarnings.class);
        assertEquals(0, beanNames.length);

        assertFalse(lbf.containsSingleton("x1"));
        assertTrue(lbf.containsBean("x1"));
        assertTrue(lbf.containsBean("&x1"));
        assertFalse(lbf.isSingleton("x1"));
        assertTrue(lbf.isSingleton("&x1"));
        assertTrue(lbf.isPrototype("x1"));
        assertFalse(lbf.isPrototype("&x1"));
        assertTrue(lbf.isTypeMatch("x1", TestBean.class));
        assertFalse(lbf.isTypeMatch("&x1", TestBean.class));
        assertTrue(lbf.isTypeMatch("&x1", DummyFactory.class));
        assertTrue(lbf.isTypeMatch("&x1", ResolvableType.forClass(DummyFactory.class)));
        assertTrue(lbf.isTypeMatch("&x1", ResolvableType.forClassWithGenerics(FactoryBean.class, Object.class)));
        assertFalse(lbf.isTypeMatch("&x1", ResolvableType.forClassWithGenerics(FactoryBean.class, String.class)));
        assertEquals(TestBean.class, lbf.getType("x1"));
        assertEquals(DummyFactory.class, lbf.getType("&x1"));
        assertTrue("prototype not instantiated", !DummyFactory.wasPrototypeCreated());
    }

    @Test
    public void testInitializedFactoryBeanFoundByNonEagerTypeMatching() {
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        Properties p = new Properties();
        p.setProperty("x1.(class)", DummyFactory.class.getName());
        // Reset static state
        DummyFactory.reset();
        p.setProperty("x1.singleton", "false");
        (new PropertiesBeanDefinitionReader(lbf)).registerBeanDefinitions(p);
        lbf.preInstantiateSingletons();

        assertTrue("prototype not instantiated", !DummyFactory.wasPrototypeCreated());
        String[] beanNames = lbf.getBeanNamesForType(TestBean.class, true, false);
        assertEquals(1, beanNames.length);
        assertEquals("x1", beanNames[0]);
        assertTrue(lbf.containsSingleton("x1"));
        assertTrue(lbf.containsBean("x1"));
        assertTrue(lbf.containsBean("&x1"));
        assertTrue(lbf.containsLocalBean("x1"));
        assertTrue(lbf.containsLocalBean("&x1"));
        assertFalse(lbf.isSingleton("x1"));
        assertTrue(lbf.isSingleton("&x1"));
        assertTrue(lbf.isPrototype("x1"));
        assertFalse(lbf.isPrototype("&x1"));
        assertTrue(lbf.isTypeMatch("x1", TestBean.class));
        assertFalse(lbf.isTypeMatch("&x1", TestBean.class));
        assertTrue(lbf.isTypeMatch("&x1", DummyFactory.class));
        assertTrue(lbf.isTypeMatch("x1", Object.class));
        assertTrue(lbf.isTypeMatch("&x1", Object.class));
        assertEquals(TestBean.class, lbf.getType("x1"));
        assertEquals(DummyFactory.class, lbf.getType("&x1"));
        assertTrue("prototype not instantiated", !DummyFactory.wasPrototypeCreated());

        lbf.registerAlias("x1", "x2");
        assertTrue(lbf.containsBean("x2"));
        assertTrue(lbf.containsBean("&x2"));
        assertTrue(lbf.containsLocalBean("x2"));
        assertTrue(lbf.containsLocalBean("&x2"));
        assertFalse(lbf.isSingleton("x2"));
        assertTrue(lbf.isSingleton("&x2"));
        assertTrue(lbf.isPrototype("x2"));
        assertFalse(lbf.isPrototype("&x2"));
        assertTrue(lbf.isTypeMatch("x2", TestBean.class));
        assertFalse(lbf.isTypeMatch("&x2", TestBean.class));
        assertTrue(lbf.isTypeMatch("&x2", DummyFactory.class));
        assertTrue(lbf.isTypeMatch("x2", Object.class));
        assertTrue(lbf.isTypeMatch("&x2", Object.class));
        assertEquals(TestBean.class, lbf.getType("x2"));
        assertEquals(DummyFactory.class, lbf.getType("&x2"));
        assertEquals(1, lbf.getAliases("x1").length);
        assertEquals("x2", lbf.getAliases("x1")[0]);
        assertEquals(1, lbf.getAliases("&x1").length);
        assertEquals("&x2", lbf.getAliases("&x1")[0]);
        assertEquals(1, lbf.getAliases("x2").length);
        assertEquals("x1", lbf.getAliases("x2")[0]);
        assertEquals(1, lbf.getAliases("&x2").length);
        assertEquals("&x1", lbf.getAliases("&x2")[0]);
    }

    @Test
    public void testStaticFactoryMethodFoundByNonEagerTypeMatching() {
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        RootBeanDefinition rbd = new RootBeanDefinition(TestBeanFactory.class);
        rbd.setFactoryMethodName("createTestBean");
        lbf.registerBeanDefinition("x1", rbd);

        TestBeanFactory.initialized = false;
        String[] beanNames = lbf.getBeanNamesForType(TestBean.class, true, false);
        assertEquals(1, beanNames.length);
        assertEquals("x1", beanNames[0]);
        assertFalse(lbf.containsSingleton("x1"));
        assertTrue(lbf.containsBean("x1"));
        assertFalse(lbf.containsBean("&x1"));
        assertTrue(lbf.isSingleton("x1"));
        assertFalse(lbf.isSingleton("&x1"));
        assertFalse(lbf.isPrototype("x1"));
        assertFalse(lbf.isPrototype("&x1"));
        assertTrue(lbf.isTypeMatch("x1", TestBean.class));
        assertFalse(lbf.isTypeMatch("&x1", TestBean.class));
        assertEquals(TestBean.class, lbf.getType("x1"));
        assertEquals(null, lbf.getType("&x1"));
        assertFalse(TestBeanFactory.initialized);
    }

    @Test
    public void testStaticPrototypeFactoryMethodFoundByNonEagerTypeMatching() {
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        RootBeanDefinition rbd = new RootBeanDefinition(TestBeanFactory.class);
        rbd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
        rbd.setFactoryMethodName("createTestBean");
        lbf.registerBeanDefinition("x1", rbd);

        TestBeanFactory.initialized = false;
        String[] beanNames = lbf.getBeanNamesForType(TestBean.class, true, false);
        assertEquals(1, beanNames.length);
        assertEquals("x1", beanNames[0]);
        assertFalse(lbf.containsSingleton("x1"));
        assertTrue(lbf.containsBean("x1"));
        assertFalse(lbf.containsBean("&x1"));
        assertFalse(lbf.isSingleton("x1"));
        assertFalse(lbf.isSingleton("&x1"));
        assertTrue(lbf.isPrototype("x1"));
        assertFalse(lbf.isPrototype("&x1"));
        assertTrue(lbf.isTypeMatch("x1", TestBean.class));
        assertFalse(lbf.isTypeMatch("&x1", TestBean.class));
        assertEquals(TestBean.class, lbf.getType("x1"));
        assertEquals(null, lbf.getType("&x1"));
        assertFalse(TestBeanFactory.initialized);
    }

    @Test
    public void testNonStaticFactoryMethodFoundByNonEagerTypeMatching() {
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        RootBeanDefinition factoryBd = new RootBeanDefinition(TestBeanFactory.class);
        lbf.registerBeanDefinition("factory", factoryBd);
        RootBeanDefinition rbd = new RootBeanDefinition(TestBeanFactory.class);
        rbd.setFactoryBeanName("factory");
        rbd.setFactoryMethodName("createTestBeanNonStatic");
        lbf.registerBeanDefinition("x1", rbd);

        TestBeanFactory.initialized = false;
        String[] beanNames = lbf.getBeanNamesForType(TestBean.class, true, false);
        assertEquals(1, beanNames.length);
        assertEquals("x1", beanNames[0]);
        assertFalse(lbf.containsSingleton("x1"));
        assertTrue(lbf.containsBean("x1"));
        assertFalse(lbf.containsBean("&x1"));
        assertTrue(lbf.isSingleton("x1"));
        assertFalse(lbf.isSingleton("&x1"));
        assertFalse(lbf.isPrototype("x1"));
        assertFalse(lbf.isPrototype("&x1"));
        assertTrue(lbf.isTypeMatch("x1", TestBean.class));
        assertFalse(lbf.isTypeMatch("&x1", TestBean.class));
        assertEquals(TestBean.class, lbf.getType("x1"));
        assertEquals(null, lbf.getType("&x1"));
        assertFalse(TestBeanFactory.initialized);
    }

    @Test
    public void testNonStaticPrototypeFactoryMethodFoundByNonEagerTypeMatching() {
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        RootBeanDefinition factoryBd = new RootBeanDefinition(TestBeanFactory.class);
        lbf.registerBeanDefinition("factory", factoryBd);
        RootBeanDefinition rbd = new RootBeanDefinition();
        rbd.setFactoryBeanName("factory");
        rbd.setFactoryMethodName("createTestBeanNonStatic");
        rbd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
        lbf.registerBeanDefinition("x1", rbd);

        TestBeanFactory.initialized = false;
        String[] beanNames = lbf.getBeanNamesForType(TestBean.class, true, false);
        assertEquals(1, beanNames.length);
        assertEquals("x1", beanNames[0]);
        assertFalse(lbf.containsSingleton("x1"));
        assertTrue(lbf.containsBean("x1"));
        assertFalse(lbf.containsBean("&x1"));
        assertTrue(lbf.containsLocalBean("x1"));
        assertFalse(lbf.containsLocalBean("&x1"));
        assertFalse(lbf.isSingleton("x1"));
        assertFalse(lbf.isSingleton("&x1"));
        assertTrue(lbf.isPrototype("x1"));
        assertFalse(lbf.isPrototype("&x1"));
        assertTrue(lbf.isTypeMatch("x1", TestBean.class));
        assertFalse(lbf.isTypeMatch("&x1", TestBean.class));
        assertTrue(lbf.isTypeMatch("x1", Object.class));
        assertFalse(lbf.isTypeMatch("&x1", Object.class));
        assertEquals(TestBean.class, lbf.getType("x1"));
        assertEquals(null, lbf.getType("&x1"));
        assertFalse(TestBeanFactory.initialized);

        lbf.registerAlias("x1", "x2");
        assertTrue(lbf.containsBean("x2"));
        assertFalse(lbf.containsBean("&x2"));
        assertTrue(lbf.containsLocalBean("x2"));
        assertFalse(lbf.containsLocalBean("&x2"));
        assertFalse(lbf.isSingleton("x2"));
        assertFalse(lbf.isSingleton("&x2"));
        assertTrue(lbf.isPrototype("x2"));
        assertFalse(lbf.isPrototype("&x2"));
        assertTrue(lbf.isTypeMatch("x2", TestBean.class));
        assertFalse(lbf.isTypeMatch("&x2", TestBean.class));
        assertTrue(lbf.isTypeMatch("x2", Object.class));
        assertFalse(lbf.isTypeMatch("&x2", Object.class));
        assertEquals(TestBean.class, lbf.getType("x2"));
        assertEquals(null, lbf.getType("&x2"));
        assertEquals(1, lbf.getAliases("x1").length);
        assertEquals("x2", lbf.getAliases("x1")[0]);
        assertEquals(1, lbf.getAliases("&x1").length);
        assertEquals("&x2", lbf.getAliases("&x1")[0]);
        assertEquals(1, lbf.getAliases("x2").length);
        assertEquals("x1", lbf.getAliases("x2")[0]);
        assertEquals(1, lbf.getAliases("&x2").length);
        assertEquals("&x1", lbf.getAliases("&x2")[0]);
    }

    @Test
    public void test() throws Exception {
        ClassLoader appClassLoader = Thread.currentThread().getContextClassLoader();

        // 添加到 excludedPackages 或 excludedClasses 的类就不会被代理的 ClassLoader 加载
        // 而会使用 JDK 默认的双亲委派机制
        // 因此 TestBean 不会被 OverridingClassLoader 重新加载，而 ITestBean 会重新加载
        OverridingClassLoader overridingClassLoader = new OverridingClassLoader(appClassLoader);
        overridingClassLoader.excludeClass(TestBean.class.getName());

        Class<?> excludedClazz1 = appClassLoader.loadClass(TestBean.class.getName());
        Class<?> excludedClazz2 = overridingClassLoader.loadClass(TestBean.class.getName());
        Assert.assertTrue("TestBean will exclude from OverridingClassLoader, so no reload",
                excludedClazz1 == excludedClazz2);

        Class<?> nonExcludedClazz1 = appClassLoader.loadClass(ITestBean.class.getName());
        Class<?> nonExcludedClazz2 = overridingClassLoader.loadClass(ITestBean.class.getName());
        Assert.assertFalse("ITestBean will not exclude, so reload again",
                nonExcludedClazz1 == nonExcludedClazz2);
    }

    @SuppressWarnings("unused")
    private static class KnowsIfInstantiated {

        private static boolean instantiated;

        public static void clearInstantiationRecord() {
            instantiated = false;
        }

        public static boolean wasInstantiated() {
            return instantiated;
        }

        public KnowsIfInstantiated() {
            instantiated = true;
        }

    }

    public static class TestBeanFactory {

        public static boolean initialized = false;

        public TestBeanFactory() {
            initialized = true;
        }

        public static TestBean createTestBean() {
            return new TestBean();
        }

        public TestBean createTestBeanNonStatic() {
            return new TestBean();
        }
    }
}
