package com.github.binarylei.validation;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidatorMain {


    @Test
    public void test1() {
        Product product = new Product();
        product.setProductName("product-01");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        violations.stream().forEach(System.out::println);
    }

    @Test
    public void test2() {
        Order order = new Order();
        order.setStatus("1");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        violations.stream().forEach(System.out::println);
    }
}
