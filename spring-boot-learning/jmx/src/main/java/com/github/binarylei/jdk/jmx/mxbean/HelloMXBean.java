package com.github.binarylei.jdk.jmx.mxbean;

import com.github.binarylei.jdk.jmx.mbean.TestBean;

public interface HelloMXBean {

    String getName();

    void setName(String name);

    void setTest(TestBean test);

    TestBean getTest();

}

