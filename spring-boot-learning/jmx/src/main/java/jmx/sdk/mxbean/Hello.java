package jmx.sdk.mxbean;

import jmx.sdk.mbean.TestBean;

import javax.management.MXBean;

@MXBean
public interface Hello {

    String getName();

    void setName(String name);

    void setTest(TestBean test);

    TestBean getTest();

}

