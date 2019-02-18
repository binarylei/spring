package com.github.binarylei.spring.annotation.import_;

import com.github.binarylei.spring.annotation.bean.User;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

// 使用 BeanDefinitionRegistry 注册
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * @param importingClassMetadata 获取标注 @Import 注解的类所有注解信息(不仅仅是 @Import)
     * @param registry               向容器中注册 BeanDefinition
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {

        boolean definition1 = registry.containsBeanDefinition("red");
        boolean definition2 = registry.containsBeanDefinition("blue");
        if (definition1 && definition2) {
            RootBeanDefinition beanDefinition = new RootBeanDefinition(User.class);
            registry.registerBeanDefinition("user2", beanDefinition);
        }
    }
}
