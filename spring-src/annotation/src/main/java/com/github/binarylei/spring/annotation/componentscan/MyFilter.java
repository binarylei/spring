package com.github.binarylei.spring.annotation.componentscan;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyFilter implements TypeFilter {

    /**
     * @param metadataReader        当前类的信息
     * @param metadataReaderFactory 获取其他类的信息
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
            throws IOException {
        // 1. 当前类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 2. 当前类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 3. 当前类的资源信息(类路径)
        Resource resource = metadataReader.getResource();
        return true;
    }
}
