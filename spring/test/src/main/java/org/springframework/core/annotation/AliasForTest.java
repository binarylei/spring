package org.springframework.core.annotation;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;


public class AliasForTest {

    @Test
    @GetMapping(value = "/GetMapping", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void test() throws NoSuchMethodException {
        Method method = ReflectUtils.findDeclaredMethod(
                AliasForTest.class, "test", null);

        RequestMapping requestMappingAnn1 = AnnotationUtils.getAnnotation(method, RequestMapping.class);
        Assert.assertEquals(new String[]{}, requestMappingAnn1.value());
        Assert.assertEquals(new String[]{}, requestMappingAnn1.consumes());

        RequestMapping requestMappingAnn2 = AnnotatedElementUtils.getMergedAnnotation(method, RequestMapping.class);
        Assert.assertEquals(new String[]{"/GetMapping"}, requestMappingAnn2.value());
        Assert.assertEquals(new String[]{MediaType.APPLICATION_JSON_VALUE}, requestMappingAnn2.consumes());
    }
}
