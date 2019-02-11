package com.github.binarylei.jmx.jdk.mxbean;

import com.github.binarylei.jmx.jdk.mbean.TestBean;

public interface HelloMXBean {

    String getName();

    void setName(String name);

    void setTest(TestBean test);

    TestBean getTest();

}

