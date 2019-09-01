package com.github.binarylei.jdk.jmx.mxbean;

import com.github.binarylei.jdk.jmx.mbean.TestBean;

import javax.management.MXBean;

@MXBean
public interface Hello {

    String getName();

    void setName(String name);

    void setTest(TestBean test);

    TestBean getTest();

}

