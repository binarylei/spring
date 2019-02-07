package com.github.binarylei.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class StatusValidator implements ConstraintValidator<Status, String> {
    private final String[] ALL_STATUS = {"created", "paid", "shipped", "closed"};

    // @Status 注解信息
    public void initialize(Status status) {
    }

    // 校验
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Arrays.asList(ALL_STATUS).contains(value))
            return true;
        return false;
    }
}
