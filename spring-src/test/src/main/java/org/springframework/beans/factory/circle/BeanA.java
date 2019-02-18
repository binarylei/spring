package org.springframework.beans.factory.circle;

public class BeanA {

    private BeanB beanB;

    public void say() {
        System.out.println(getClass().getSimpleName());
    }

    public BeanB getBeanB() {
        return beanB;
    }

    public void setBeanB(BeanB beanB) {
        this.beanB = beanB;
    }
}
