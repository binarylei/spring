package com.github.binarylei.spring.annotation.import_;

import com.github.binarylei.spring.annotation.bean.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

public class AnnnotationTest {

    @Test
    public void testImportSelector() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AnnnotationImportSelector.class);

        Assert.assertTrue(context.containsBean("user"));
    }

    @Import({User.class, MyImportSelector.class})
    public class AnnnotationImportSelector {
    }

    @Import({User.class, MyImportBeanDefinitionRegistrar.class})
    public class AnnnotationImportBeanDefinitionRegistrar {
    }
}
