package org.springframework.core;

import org.junit.Test;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author: leigang
 * @version: 2019-01-26
 */
public class ParameterNameDiscovererTest {

    @Test
    public void testStandardReflectionParameterNameDiscoverer() {
        ParameterNameDiscoverer discoverer = new StandardReflectionParameterNameDiscoverer();
        discoverer = new LocalVariableTableParameterNameDiscoverer();
        discoverer = new DefaultParameterNameDiscoverer();

        Method method = ReflectionUtils.findMethod(InnerClass.class, "method", List.class);
        String[] actualParams = discoverer.getParameterNames(method);
        assertThat(actualParams, is(new String[]{"users"}));
    }

    public class InnerClass {
        public void method(@PathVariable("username") @RequestBody final List<Map<String, List<Integer>>> users) {
        }
    }

}
