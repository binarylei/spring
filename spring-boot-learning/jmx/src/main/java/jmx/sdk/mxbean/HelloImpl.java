package jmx.sdk.mxbean;

import jmx.sdk.mbean.TestBean;

public class HelloImpl implements Hello {

    private String name;
    private TestBean test;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setTest(TestBean test) {
        this.test = test;
    }

    @Override
    public TestBean getTest() {
        return this.test;
    }

}
