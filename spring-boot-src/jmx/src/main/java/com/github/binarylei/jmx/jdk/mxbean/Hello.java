package com.github.binarylei.jmx.jdk.mxbean;

import com.github.binarylei.jmx.jdk.mbean.TestBean;

import javax.management.MXBean;

@MXBean
public interface Hello {

    String getName();

    void setName(String name);

    void setTest(TestBean test);

    TestBean getTest();

}

