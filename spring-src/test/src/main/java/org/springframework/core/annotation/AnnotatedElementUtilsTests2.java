/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.core.annotation;

import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtilsTests.MyRepeatable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

public class AnnotatedElementUtilsTests2 {

    @Test
    public void test1() {
        Set<MyRepeatable> allMergedAnnotations = AnnotatedElementUtils.getMergedRepeatableAnnotations(RepeatableClass.class, MyRepeatable.class);
        allMergedAnnotations.forEach(System.out::println);
    }

    @Test
    public void test2() {
        Annotation[] annotations = RepeatableClass.class.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }

    @Test
    @GetMapping("/GetMapping")
    public void test3() throws Exception {
        Method method = AnnotatedElementUtilsTests2.class.getDeclaredMethod("test3", null);
        Set<GetMapping> getMappingsSet = AnnotatedElementUtils.getAllMergedAnnotations(method, GetMapping.class);
        Set<RequestMapping> requestMappingSet = AnnotatedElementUtils.getAllMergedAnnotations(method, RequestMapping.class);
        getMappingsSet.forEach((annotation) -> {
            System.out.println(Arrays.toString(annotation.value()));
        });

        requestMappingSet.forEach((annotation) -> {
            System.out.println(annotation.method());
            System.out.println(Arrays.toString(annotation.value()));
        });

        Set<Component> allMergedAnnotations = AnnotatedElementUtils.getAllMergedAnnotations(RepeatableClass2.class, Component.class);
        allMergedAnnotations.forEach(System.out::println);
    }

    @Component
    public class RepeatableClass2 {
    }

    @MyRepeatable("1")
    @MyRepeatable("2")
    public class RepeatableClass {
    }

}