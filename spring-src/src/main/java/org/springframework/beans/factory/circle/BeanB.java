package org.springframework.beans.factory.circle;

public class BeanB {

    private BeanA beanA;

    public void say() {
        System.out.println(getClass().getSimpleName());
    }

    public BeanA getBeanA() {
        return beanA;
    }

    public void setBeanA(BeanA beanA) {
        this.beanA = beanA;
    }
}
