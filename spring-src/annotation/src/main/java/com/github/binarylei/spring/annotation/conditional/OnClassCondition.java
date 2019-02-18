package com.github.binarylei.spring.annotation.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.List;

public class OnClassCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        MultiValueMap<String, Object> attributes = metadata.getAllAnnotationAttributes(ConditionalOnClass.class.getName());
        List<Object> classNameList = attributes.get("name");
        for (Object c : classNameList) {
            String[] classNameArr = (String[]) c;
            for (String className : classNameArr) {
                try {
                    Class.forName((String) className);
                    return true;
                } catch (ClassNotFoundException ingore) {
                }
            }
        }
        return false;
    }
}
