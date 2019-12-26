package jmx.sdk.mxbean;

import jmx.sdk.mbean.TestBean;

public interface HelloMXBean {

    String getName();

    void setName(String name);

    void setTest(TestBean test);

    TestBean getTest();

}

