package org.springframework.core;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ParameterTest {

    public static void main(String[] args) throws Exception {
        Method method = ParameterTest.class.getMethod( "main", String[].class );
        for( final Parameter parameter: method.getParameters() ) {
            System.out.println("isNamePresent: " + parameter.isNamePresent());
            System.out.println( "Parameter: " + parameter.getName() );
        }
    }
}
