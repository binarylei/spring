package com.github.binarylei.spring.annotation.import_;

import com.github.binarylei.spring.annotation.bean.Person;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

// 返回类名的全定限名称
public class MyImportSelector implements ImportSelector {

    /**
     * @param importingClassMetadata 获取标注 @Import 注解的类所有注解信息(不仅仅是 @Import)
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 不要返回 null，否则会报空指针异常
        return new String[]{Person.class.getName()};
    }
}
